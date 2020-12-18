package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.stream.IntStream;

@Data
public final class InvDefinition implements Definition {

    private int id;
    private int size;

    public InvDefinition(int id) {
        this.id = id;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        while (true) {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 2) {
                size = buffer.getShort() & 0xffff;
            }
        }
    }

}
