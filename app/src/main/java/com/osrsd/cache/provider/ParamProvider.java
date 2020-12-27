package com.osrsd.cache.provider;

import com.osrsd.cache.def.ParamDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class ParamProvider {

    public static Definition decode(ByteBuffer buffer, ParamDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setType((char) buffer.get());
            } else if (opcode == 2) {
                definition.setDefaultInt(buffer.getInt());
            } else if (opcode == 4) {
                definition.setMembers(false);
            } else if (opcode == 5) {
                definition.setDefaultString(ByteBufferExt.INSTANCE.getString(buffer));
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
