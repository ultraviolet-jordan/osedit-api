package com.osrsd.cache.provider;

import com.osrsd.cache.def.AreaDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class AreaProvider {

    public static Definition decode(ByteBuffer buffer, AreaDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                definition.setSpriteId(buffer.getShort() & 0xffff);
            } else if (opcode == 2) {
                definition.setField3294(buffer.getShort() & 0xffff);
            } else if (opcode == 3) {
                definition.setName(ByteBufferExt.INSTANCE.getString(buffer));
            } else if (opcode == 4) {
                definition.setField3296(ByteBufferExt.INSTANCE.getMedium(buffer));
            } else if (opcode == 5) {
                ByteBufferExt.INSTANCE.getMedium(buffer);
            } else if (opcode == 6) {
                definition.setField3310(buffer.get() & 0xff);
            } else if (opcode == 7) {
                buffer.get();
            } else if (opcode == 8) {
                buffer.get();
            } else if (opcode >= 10 && opcode <= 14) {
                definition.getField3298()[opcode - 10] = ByteBufferExt.INSTANCE.getString(buffer);
            } else if (opcode == 15) {
                int var3 = buffer.get() & 0xff;
                definition.setField3300(new int[var3 * 2]);
                int var4;
                for (var4 = 0; var4 < var3 * 2; ++var4) {
                    definition.getField3300()[var4] = buffer.getShort();
                }
                buffer.getInt();
                var4 = buffer.get() & 0xff;
                definition.setField3292(new int[var4]);
                int var5;
                for (var5 = 0; var5 < definition.getField3292().length; ++var5) {
                    definition.getField3292()[var5] = buffer.getInt();
                }
                definition.setField3309(new byte[var3]);
                for (var5 = 0; var5 < var3; ++var5) {
                    definition.getField3309()[var5] = buffer.get();
                }
            } else if (opcode == 17) {
                definition.setField3308(ByteBufferExt.INSTANCE.getString(buffer));
            } else if (opcode == 18) {
                buffer.getShort();
            } else if (opcode == 19) {
                definition.setField3297(buffer.getShort() & 0xffff);
            } else if (opcode == 21) {
                buffer.getInt();
            } else if (opcode == 22) {
                buffer.getInt();
            } else if (opcode == 23) {
                buffer.get();
                buffer.get();
                buffer.get();
            } else if (opcode == 24) {
                buffer.getShort();
                buffer.getShort();
            } else if (opcode == 25) {
                buffer.getShort();
            } else if (opcode == 28) {
                buffer.get();
            } else if (opcode == 29) {
                buffer.get();
            } else if (opcode == 30) {
                buffer.get();
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
