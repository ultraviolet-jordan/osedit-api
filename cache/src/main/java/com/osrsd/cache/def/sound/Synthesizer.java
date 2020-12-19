package com.osrsd.cache.def.sound;

import lombok.Data;

import java.nio.ByteBuffer;

@Data
public class Synthesizer {
    
    private int[] pairs;
    private int[][][] phases;
    private int[][][] magnitudes;
    private int[] unity;
    private int[][] coefficients;
    private int forwardMultiplier;

    public Synthesizer() {
        pairs = new int[2];
        phases = new int[2][2][4];
        magnitudes = new int[2][2][4];
        unity = new int[2];
        coefficients = new int[2][8];
    }

    public void decode(ByteBuffer buffer, AudioEnvelope audioEnvelope) {
        int var3 = buffer.get() & 0xff;
        pairs[0] = var3 >> 4;
        pairs[1] = var3 & 15;
        if (var3 != 0) {
            unity[0] = buffer.getShort() & 0xffff;
            unity[1] = buffer.getShort() & 0xffff;
            int var4 = buffer.get() & 0xff;
            int var5;
            int var6;
            for (var5 = 0; var5 < 2; ++var5) {
                for (var6 = 0; var6 < pairs[var5]; ++var6) {
                    phases[var5][0][var6] = buffer.getShort() & 0xffff;
                    magnitudes[var5][0][var6] = buffer.getShort() & 0xffff;
                }
            }
            for (var5 = 0; var5 < 2; ++var5) {
                for (var6 = 0; var6 < pairs[var5]; ++var6) {
                    if ((var4 & 1 << var5 * 4 << var6) != 0) {
                        phases[var5][1][var6] = buffer.getShort() & 0xffff;
                        magnitudes[var5][1][var6] = buffer.getShort() & 0xffff;
                    } else {
                        phases[var5][1][var6] = phases[var5][0][var6];
                        magnitudes[var5][1][var6] = magnitudes[var5][0][var6];
                    }
                }
            }
            if (var4 != 0 || unity[1] != unity[0]) {
                audioEnvelope.decodeSegments(buffer);
            }
        } else {
            int[] var7 = unity;
            unity[1] = 0;
            var7[0] = 0;
        }
    }

    public int compute(int var1, float var2) {
        float[][] minimisedCoefficients = new float[2][8];
        float coefficientMultiplier = 0;
        float var3;
        if (var1 == 0) {
            var3 = (float) unity[0] + (float) (unity[1] - unity[0]) * var2;
            var3 *= 0.0030517578F;
            coefficientMultiplier = (float) Math.pow(0.1D, var3 / 20.0F);
            forwardMultiplier = (int) (coefficientMultiplier * 65536.0F);
        }
        if (pairs[var1] == 0) {
            return 0;
        } else {
            var3 = magnitudes(var1, 0, var2);
            minimisedCoefficients[var1][0] = -2.0F * var3 * (float) Math.cos(phases(var1, 0, var2));
            minimisedCoefficients[var1][1] = var3 * var3;
            int var4;
            for (var4 = 1; var4 < pairs[var1]; ++var4) {
                var3 = magnitudes(var1, var4, var2);
                float var5 = -2.0F * var3 * (float) Math.cos(phases(var1, var4, var2));
                float var6 = var3 * var3;
                minimisedCoefficients[var1][var4 * 2 + 1] = minimisedCoefficients[var1][var4 * 2 - 1] * var6;
                minimisedCoefficients[var1][var4 * 2] = minimisedCoefficients[var1][var4 * 2 - 1] * var5 + minimisedCoefficients[var1][var4 * 2 - 2] * var6;
                for (int var7 = var4 * 2 - 1; var7 >= 2; --var7) {
                    minimisedCoefficients[var1][var7] += minimisedCoefficients[var1][var7 - 1] * var5 + minimisedCoefficients[var1][var7 - 2] * var6;
                }
                minimisedCoefficients[var1][1] += minimisedCoefficients[var1][0] * var5 + var6;
                minimisedCoefficients[var1][0] += var5;
            }
            if (var1 == 0) {
                for (var4 = 0; var4 < pairs[0] * 2; ++var4) {
                    minimisedCoefficients[0][var4] *= coefficientMultiplier;
                }
            }
            for (var4 = 0; var4 < pairs[var1] * 2; ++var4) {
                coefficients[var1][var4] = (int) (minimisedCoefficients[var1][var4] * 65536.0F);
            }
            return pairs[var1] * 2;
        }
    }

    private float magnitudes(int var1, int var2, float var3) {
        float var4 = (float) magnitudes[var1][0][var2] + var3 * (float) (magnitudes[var1][1][var2] - magnitudes[var1][0][var2]);
        var4 *= 0.0015258789F;
        return 1.0F - (float) Math.pow(10.0D, -var4 / 20.0F);
    }

    private float phases(int var1, int var2, float var3) {
        float var4 = (float) phases[var1][0][var2] + var3 * (float) (phases[var1][1][var2] - phases[var1][0][var2]);
        var4 *= 1.2207031E-4F;
        return (32.703197F * (float) Math.pow(2.0D, var4)) * 3.1415927F / 11025.0F;
    }

}
