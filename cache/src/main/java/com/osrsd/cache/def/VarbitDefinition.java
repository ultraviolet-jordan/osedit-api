package com.osrsd.cache.def;

import lombok.Data;

import java.nio.ByteBuffer;

@Data
public final class VarbitDefinition implements Definition {

    private int id;
    private int index;
    private int leastSignificantBit;
    private int mostSignificantBit;

    public VarbitDefinition(int id) {
        this.id = id;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        while (true) {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                index = buffer.getShort() & 0xffff;
                leastSignificantBit = buffer.get() & 0xff;
                mostSignificantBit = buffer.get() & 0xff;
            }
        }
    }

}
