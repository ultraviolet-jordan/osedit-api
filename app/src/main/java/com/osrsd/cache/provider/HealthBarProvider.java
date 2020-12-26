package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.HealthbarDefinition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class HealthBarProvider {

    public static Definition decode(ByteBuffer buffer, HealthbarDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                buffer.getShort();
            } else if (opcode == 2) {
                definition.setField3277(buffer.get() & 0xff);
            } else if (opcode == 3) {
                definition.setField3278(buffer.get() & 0xff);
            } else if (opcode == 4) {
                definition.setField3283(0);
            } else if (opcode == 5) {
                definition.setField3275(buffer.getShort() & 0xffff);
            } else if (opcode == 6) {
                buffer.get();
            } else if (opcode == 7) {
                definition.setHealthBarFrontSpriteId(buffer.getShort() & 0xffff);
            } else if (opcode == 8) {
                definition.setHealthBarBackSpriteId(buffer.getShort() & 0xffff);
            } else if (opcode == 11) {
                definition.setField3283(buffer.getShort() & 0xffff);
            } else if (opcode == 14) {
                definition.setHealthScale(buffer.get() & 0xff);
            } else if (opcode == 15) {
                definition.setHealthBarPadding(buffer.get() & 0xff);
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
