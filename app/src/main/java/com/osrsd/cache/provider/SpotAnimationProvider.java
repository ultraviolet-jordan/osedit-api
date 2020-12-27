package com.osrsd.cache.provider;

import com.osrsd.cache.def.SpotAnimationDefinition;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Slf4j
public final class SpotAnimationProvider {

    public static Definition decode(ByteBuffer buffer, SpotAnimationDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setModelId(buffer.getShort() & 0xffff);
            } else if (opcode == 2) {
                definition.setAnimationId(buffer.getShort() & 0xffff);
            } else if (opcode == 4) {
                definition.setResizeX(buffer.getShort() & 0xffff);
            } else if (opcode == 5) {
                definition.setResizeY(buffer.getShort() & 0xffff);
            } else if (opcode == 6) {
                definition.setRotation(buffer.getShort() & 0xffff);
            } else if (opcode == 7) {
                definition.setAmbient(buffer.get() & 0xff);
            } else if (opcode == 8) {
                definition.setContrast(buffer.get() & 0xff);
            } else if (opcode == 40) {
                int size = buffer.get() & 0xff;
                definition.setRecolorToFind(new short[size]);
                definition.setRecolorToReplace(new short[size]);
                IntStream.range(0, size).forEach(var4 -> {
                    definition.getRecolorToFind()[var4] = (short) (buffer.getShort() & 0xffff);
                    definition.getRecolorToReplace()[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else if (opcode == 41) {
                int size = buffer.get() & 0xff;
                definition.setTextureToFind(new short[size]);
                definition.setTextureToReplace(new short[size]);
                IntStream.range(0, size).forEach(var4 -> {
                    definition.getTextureToFind()[var4] = (short) (buffer.getShort() & 0xffff);
                    definition.getTextureToReplace()[var4] = (short) (buffer.getShort() & 0xffff);
                });
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
