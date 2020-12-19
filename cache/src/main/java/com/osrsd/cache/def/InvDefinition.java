package com.osrsd.cache.def;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Data
@Slf4j
public final class InvDefinition implements Definition {

    private int id;
    private int size;

    public InvDefinition(int id) {
        this.id = id;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 2) {
                size = buffer.getShort() & 0xffff;
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
