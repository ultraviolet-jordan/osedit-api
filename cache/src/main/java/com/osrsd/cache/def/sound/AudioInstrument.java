package com.osrsd.cache.def.sound;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;

import java.nio.ByteBuffer;

@Data
public final class AudioInstrument {

    private AudioEnvelope pitch;
    private AudioEnvelope volume;
    private AudioEnvelope pitchModifier;
    private AudioEnvelope pitchModifierAmplitude;
    private AudioEnvelope volumeMultiplier;
    private AudioEnvelope volumeMultiplierAmplitude;
    private AudioEnvelope release;
    private AudioEnvelope field1653;
    private int[] oscillatorVolume;
    private int[] oscillatorPitch;
    private int[] oscillatorDelays;
    private int delayTime;
    private int delayDecay;
    private Synthesizer filter;
    private AudioEnvelope filterEnvelope;
    private int duration;
    private int offset;
    private int[] phases;
    private int[] delays;
    private int[] volumeSteps;
    private int[] pitchSteps;
    private int[] pitchBaseSteps;

    public AudioInstrument() {
        oscillatorVolume = new int[]{0, 0, 0, 0, 0};
        oscillatorPitch = new int[]{0, 0, 0, 0, 0};
        oscillatorDelays = new int[]{0, 0, 0, 0, 0};
        delayTime = 0;
        delayDecay = 100;
        duration = 500;
        offset = 0;
        phases = new int[5];
        delays = new int[5];
        volumeSteps = new int[5];
        pitchSteps = new int[5];
        pitchBaseSteps = new int[5];
    }

    public static void method3854(int[] var0, int var1, int var2) {
        for (var2 = var2 + var1 - 7; var1 < var2; var0[var1++] = 0) {
            var0[var1++] = 0;
            var0[var1++] = 0;
            var0[var1++] = 0;
            var0[var1++] = 0;
            var0[var1++] = 0;
            var0[var1++] = 0;
            var0[var1++] = 0;
        }

        var2 += 7;
        while (var1 < var2) {
            var0[var1++] = 0;
        }
    }

    public final void decode(ByteBuffer buffer) {
        pitch = new AudioEnvelope();
        pitch.decode(buffer);
        volume = new AudioEnvelope();
        volume.decode(buffer);
        int var2 = buffer.get() & 0xff;
        if (var2 != 0) {
            buffer.position(buffer.position() - 1);
            pitchModifier = new AudioEnvelope();
            pitchModifier.decode(buffer);
            pitchModifierAmplitude = new AudioEnvelope();
            pitchModifierAmplitude.decode(buffer);
        }

        var2 = buffer.get() & 0xff;
        if (var2 != 0) {
            buffer.position(buffer.position() - 1);
            volumeMultiplier = new AudioEnvelope();
            volumeMultiplier.decode(buffer);
            volumeMultiplierAmplitude = new AudioEnvelope();
            volumeMultiplierAmplitude.decode(buffer);
        }

        var2 = buffer.get() & 0xff;
        if (var2 != 0) {
            buffer.position(buffer.position() - 1);
            release = new AudioEnvelope();
            release.decode(buffer);
            field1653 = new AudioEnvelope();
            field1653.decode(buffer);
        }

        for (int var3 = 0; var3 < 10; ++var3) {
            int var4 = ByteBufferExt.getUnsignedShortSmart(buffer);
            if (var4 == 0) {
                break;
            }

            oscillatorVolume[var3] = var4;
            oscillatorPitch[var3] = ByteBufferExt.getShortSmart(buffer);
            oscillatorDelays[var3] = ByteBufferExt.getUnsignedShortSmart(buffer);
        }

        delayTime = ByteBufferExt.getUnsignedShortSmart(buffer);
        delayDecay = ByteBufferExt.getUnsignedShortSmart(buffer);
        duration = buffer.getShort() & 0xffff;
        offset = buffer.getShort() & 0xffff;
        filter = new Synthesizer();
        filterEnvelope = new AudioEnvelope();
        filter.decode(buffer, filterEnvelope);
    }

    public final int[] synthesize(int var1, int var2) {
        int[] samples = AudioMixer.SAMPLES;
        method3854(samples, 0, var1);
        if (var2 >= 10) {
            double var3 = (double) var1 / ((double) var2 + 0.0D);
            pitch.reset();
            volume.reset();
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            if (pitchModifier != null) {
                pitchModifier.reset();
                pitchModifierAmplitude.reset();
                var5 = (int) ((double) (pitchModifier.getEnd() - pitchModifier.getStart()) * 32.768D / var3);
                var6 = (int) ((double) pitchModifier.getStart() * 32.768D / var3);
            }
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;
            if (volumeMultiplier != null) {
                volumeMultiplier.reset();
                volumeMultiplierAmplitude.reset();
                var8 = (int) ((double) (volumeMultiplier.getEnd() - volumeMultiplier.getStart()) * 32.768D / var3);
                var9 = (int) ((double) volumeMultiplier.getStart() * 32.768D / var3);
            }
            int var11;
            for (var11 = 0; var11 < 5; ++var11) {
                if (oscillatorVolume[var11] != 0) {
                    phases[var11] = 0;
                    delays[var11] = (int) ((double) oscillatorDelays[var11] * var3);
                    volumeSteps[var11] = (oscillatorVolume[var11] << 14) / 100;
                    pitchSteps[var11] = (int) ((double) (pitch.getEnd() - pitch.getStart()) * 32.768D * Math.pow(1.0057929410678534D, oscillatorPitch[var11]) / var3);
                    pitchBaseSteps[var11] = (int) ((double) pitch.getStart() * 32.768D / var3);
                }
            }
            int var12;
            int var13;
            int var14;
            int var15;
            for (var11 = 0; var11 < var1; ++var11) {
                var12 = pitch.step(var1);
                var13 = volume.step(var1);
                if (pitchModifier != null) {
                    var14 = pitchModifier.step(var1);
                    var15 = pitchModifierAmplitude.step(var1);
                    var12 += AudioMixer.evaluateWave(var7, var15, pitchModifier.getForm()) >> 1;
                    var7 = var7 + var6 + (var14 * var5 >> 16);
                }
                if (volumeMultiplier != null) {
                    var14 = volumeMultiplier.step(var1);
                    var15 = volumeMultiplierAmplitude.step(var1);
                    var13 = var13 * ((AudioMixer.evaluateWave(var10, var15, volumeMultiplier.getForm()) >> 1) + 32768) >> 15;
                    var10 = var10 + var9 + (var14 * var8 >> 16);
                }
                for (var14 = 0; var14 < 5; ++var14) {
                    if (oscillatorVolume[var14] != 0) {
                        var15 = delays[var14] + var11;
                        if (var15 < var1) {
                            samples[var15] += AudioMixer.evaluateWave(phases[var14], var13 * volumeSteps[var14] >> 15, pitch.getForm());
                            phases[var14] += (var12 * pitchSteps[var14] >> 16) + pitchBaseSteps[var14];
                        }
                    }
                }
            }
            int var16;
            if (release != null) {
                release.reset();
                field1653.reset();
                var11 = 0;
                boolean var20 = true;
                for (var14 = 0; var14 < var1; ++var14) {
                    var15 = release.step(var1);
                    var16 = field1653.step(var1);
                    if (var20) {
                        var12 = (var15 * (release.getEnd() - release.getStart()) >> 8) + release.getStart();
                    } else {
                        var12 = (var16 * (release.getEnd() - release.getStart()) >> 8) + release.getStart();
                    }
                    var11 += 256;
                    if (var11 >= var12) {
                        var11 = 0;
                        var20 = !var20;
                    }
                    if (var20) {
                        samples[var14] = 0;
                    }
                }
            }
            if (delayTime > 0 && delayDecay > 0) {
                var11 = (int) ((double) delayTime * var3);

                for (var12 = var11; var12 < var1; ++var12) {
                    samples[var12] += samples[var12 - var11] * delayDecay / 100;
                }
            }
            if (filter.getPairs()[0] > 0 || filter.getPairs()[1] > 0) {
                filterEnvelope.reset();
                var11 = filterEnvelope.step(var1 + 1);
                var12 = filter.compute(0, (float) var11 / 65536.0F);
                var13 = filter.compute(1, (float) var11 / 65536.0F);
                if (var1 >= var12 + var13) {
                    var14 = 0;
                    var15 = Math.min(var13, var1 - var12);
                    int var17;
                    while (var14 < var15) {
                        var16 = (int) ((long) samples[var14 + var12] * (long) filter.getForwardMultiplier() >> 16);
                        for (var17 = 0; var17 < var12; ++var17) {
                            var16 += (int) ((long) samples[var14 + var12 - 1 - var17] * (long) filter.getCoefficients()[0][var17] >> 16);
                        }
                        for (var17 = 0; var17 < var14; ++var17) {
                            var16 -= (int) ((long) samples[var14 - 1 - var17] * (long) filter.getCoefficients()[1][var17] >> 16);
                        }
                        samples[var14] = var16;
                        var11 = filterEnvelope.step(var1 + 1);
                        ++var14;
                    }
                    var15 = 128;
                    while (true) {
                        if (var15 > var1 - var12) {
                            var15 = var1 - var12;
                        }
                        int var18;
                        while (var14 < var15) {
                            var17 = (int) ((long) samples[var14 + var12] * (long) filter.getForwardMultiplier() >> 16);
                            for (var18 = 0; var18 < var12; ++var18) {
                                var17 += (int) ((long) samples[var14 + var12 - 1 - var18] * (long) filter.getCoefficients()[0][var18] >> 16);
                            }
                            for (var18 = 0; var18 < var13; ++var18) {
                                var17 -= (int) ((long) samples[var14 - 1 - var18] * (long) filter.getCoefficients()[1][var18] >> 16);
                            }
                            samples[var14] = var17;
                            var11 = filterEnvelope.step(var1 + 1);
                            ++var14;
                        }
                        if (var14 >= var1 - var12) {
                            while (var14 < var1) {
                                var17 = 0;
                                for (var18 = var14 + var12 - var1; var18 < var12; ++var18) {
                                    var17 += (int) ((long) samples[var14 + var12 - 1 - var18] * (long) filter.getCoefficients()[0][var18] >> 16);
                                }
                                for (var18 = 0; var18 < var13; ++var18) {
                                    var17 -= (int) ((long) samples[var14 - 1 - var18] * (long) filter.getCoefficients()[1][var18] >> 16);
                                }
                                samples[var14] = var17;
                                filterEnvelope.step(var1 + 1);
                                ++var14;
                            }
                            break;
                        }
                        var12 = filter.compute(0, (float) var11 / 65536.0F);
                        var13 = filter.compute(1, (float) var11 / 65536.0F);
                        var15 += 128;
                    }
                }
            }
            for (var11 = 0; var11 < var1; ++var11) {
                if (samples[var11] < -32768) {
                    samples[var11] = -32768;
                }
                if (samples[var11] > 32767) {
                    samples[var11] = 32767;
                }
            }
        }
        return samples;
    }

}
