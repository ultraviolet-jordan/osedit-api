package com.osrsd.cache.def.sound;

import lombok.Data;

import java.nio.ByteBuffer;

@Data
public class AudioEnvelope {

    private int segments;
    private int[] durations;
    private int[] phases;
    private int start;
    private int end;
    private int form;
    private int ticks;
    private int phaseIndex;
    private int step;
    private int amplitude;
    private int max;

    public AudioEnvelope() {
        segments = 2;
        durations = new int[2];
        phases = new int[2];
        durations[1] = 65535;
        phases[1] = 65535;
    }

    public void decode(ByteBuffer buffer) {
        form = buffer.get() & 0xff;
        start = buffer.getInt();
        end = buffer.getInt();
        decodeSegments(buffer);
    }

    public void decodeSegments(ByteBuffer buffer) {
        segments = buffer.get() & 0xff;
        durations = new int[segments];
        phases = new int[segments];
        for (int segment = 0; segment < segments; ++segment) {
            durations[segment] = buffer.getShort() & 0xffff;
            phases[segment] = buffer.getShort() & 0xffff;
        }
    }

    public void reset() {
        ticks = 0;
        phaseIndex = 0;
        step = 0;
        amplitude = 0;
        max = 0;
    }

    public int step(int var1) {
        if (max >= ticks) {
            amplitude = phases[phaseIndex++] << 15;
            if (phaseIndex >= segments) {
                phaseIndex = segments - 1;
            }

            ticks = (int) ((double) durations[phaseIndex] / 65536.0D * (double) var1);
            if (ticks > max) {
                step = ((phases[phaseIndex] << 15) - amplitude) / (ticks - max);
            }
        }

        amplitude += step;
        ++max;
        return amplitude - step >> 15;
    }
}
