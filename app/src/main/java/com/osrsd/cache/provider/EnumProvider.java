package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.EnumDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Slf4j
public final class EnumProvider {

    public static Definition decode(ByteBuffer buffer, EnumDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setKeyType((char) (buffer.get() & 0xff));
            } else if (opcode == 2) {
                definition.setValType((char) (buffer.get() & 0xff));
            } else if (opcode == 3) {
                definition.setDefaultString(ByteBufferExt.getString(buffer));
            } else if (opcode == 4) {
                definition.setDefaultInt(buffer.getInt());
            } else if (opcode == 5) {
                int size = buffer.getShort() & 0xffff;
                IntStream.range(0, size).map(index -> buffer.getInt()).forEach(key -> {
                    String value = ByteBufferExt.getString(buffer);
                    definition.getValues().put((long) key, value);
                });
            } else if (opcode == 6) {
                int size = buffer.getShort() & 0xffff;
                IntStream.range(0, size).map(index -> buffer.getInt()).forEach(key -> {
                    int value = buffer.getInt();
                    definition.getValues().put((long) key, value);
                });
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
