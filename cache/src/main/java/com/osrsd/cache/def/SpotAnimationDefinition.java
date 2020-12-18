package com.osrsd.cache.def;

import lombok.Data;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Data
public final class SpotAnimationDefinition implements Definition {

    private final int id;
    private int rotation;
    private short[] textureToReplace;
    private short[] textureToFind;
    private int resizeY;
    private int animationId;
    private short[] recolorToFind;
    private short[] recolorToReplace;
    private int resizeX;
    private int modelId;
    private int ambient;
    private int contrast;

    public SpotAnimationDefinition(int id) {
        this.id = id;
        rotation = 0;
        resizeY = 128;
        animationId = -1;
        resizeX = 128;
        ambient = 0;
        contrast = 0;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        while (true) {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                modelId = buffer.getShort() & 0xffff;
            } else if (opcode == 2) {
                animationId = buffer.getShort() & 0xffff;
            } else if (opcode == 4) {
                resizeX = buffer.getShort() & 0xffff;
            } else if (opcode == 5) {
                resizeY = buffer.getShort() & 0xffff;
            } else if (opcode == 6) {
                rotation = buffer.getShort() & 0xffff;
            } else if (opcode == 7) {
                ambient = buffer.get() & 0xff;
            } else if (opcode == 8) {
                contrast = buffer.get() & 0xff;
            } else if (opcode == 40) {
                int size = buffer.get() & 0xff;
                recolorToFind = new short[size];
                recolorToReplace = new short[size];
                IntStream.range(0, size).forEach(var4 -> {
                    recolorToFind[var4] = (short) (buffer.getShort() & 0xffff);
                    recolorToReplace[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 41) {
                int size = buffer.get() & 0xff;
                textureToFind = new short[size];
                textureToReplace = new short[size];
                IntStream.range(0, size).forEach(var4 -> {
                    textureToFind[var4] = (short) (buffer.getShort() & 0xffff);
                    textureToReplace[var4] = (short) (buffer.getShort() & 0xffff);
                });
            }
        }
    }

}
