package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.InvDefinition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class InvProvider {

    public static Definition decode(ByteBuffer buffer, InvDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 2) {
                definition.setSize(buffer.getShort() & 0xffff);
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
