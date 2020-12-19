package com.osrsd.cache.def;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Data
@Slf4j
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
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                index = buffer.getShort() & 0xffff;
                leastSignificantBit = buffer.get() & 0xff;
                mostSignificantBit = buffer.get() & 0xff;
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
