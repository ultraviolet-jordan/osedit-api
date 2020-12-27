package com.osrsd.cache.provider;

import com.osrsd.cache.def.VarbitDefinition;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class VarbitProvider {

    public static Definition decode(ByteBuffer buffer, VarbitDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setIndex(buffer.getShort() & 0xffff);
                definition.setLeastSignificantBit(buffer.get() & 0xff);
                definition.setMostSignificantBit(buffer.get() & 0xff);
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
