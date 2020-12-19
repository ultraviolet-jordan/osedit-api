package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.stream.IntStream;

@Data
@Slf4j
public final class EnumDefinition implements Definition {

    private int id;
    private char keyType;
    private char valType;
    private String defaultString;
    private int defaultInt;
    private HashMap<Long, Object> values;

    public EnumDefinition(int id) {
        this.id = id;
        defaultString = "null";
        values = new HashMap<>();
    }

    @Override
    public void decode(ByteBuffer buffer) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                keyType = (char) (buffer.get() & 0xff);
            } else if (opcode == 2) {
                valType = (char) (buffer.get() & 0xff);
            } else if (opcode == 3) {
                defaultString = ByteBufferExt.getString(buffer);
            } else if (opcode == 4) {
                defaultInt = buffer.getInt();
            } else if (opcode == 5) {
                int size = buffer.getShort() & 0xffff;
                IntStream.range(0, size).map(index -> buffer.getInt()).forEach(key -> {
                    String value = ByteBufferExt.getString(buffer);
                    values.put((long) key, value);
                });
            } else if (opcode == 6) {
                int size = buffer.getShort() & 0xffff;
                IntStream.range(0, size).map(index -> buffer.getInt()).forEach(key -> {
                    int value = buffer.getInt();
                    values.put((long) key, value);
                });
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
