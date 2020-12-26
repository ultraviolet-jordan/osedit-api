package com.osrsd.cache.def.sound;

import java.util.Random;
import java.util.stream.IntStream;

public class AudioMixer {

    public static int[] NOISE;
    public static int[] AUDIO_SINE;
    public static int[] SAMPLES;

    static {
        NOISE = new int[Short.MAX_VALUE + 1];
        AUDIO_SINE = new int[Short.MAX_VALUE + 1];
        SAMPLES = new int[220_500];
        IntStream.rangeClosed(0, Short.MAX_VALUE).forEach(index -> NOISE[index] = (new Random().nextInt() & 2) - 1);
        IntStream.rangeClosed(0, Short.MAX_VALUE).forEach(index -> AUDIO_SINE[index] = (int) (Math.sin((double) index / 5215.1903D) * 16384.0D));
    }

    public static int evaluateWave(int var1, int var2, int var3) {
        return var3 == 1 ? ((var1 & 32767) < 16384 ? var2 : -var2)
                : (var3 == 2 ? AUDIO_SINE[var1 & 32767] * var2 >> 14
                : (var3 == 3 ? (var2 * (var1 & 32767) >> 14) - var2
                : (var3 == 4 ? var2 * NOISE[var1 / 2607 & 32767] : 0)));
    }

}
