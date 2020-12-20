package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Data
@Slf4j
public final class ParamDefinition implements Definition {

    private int id;
    private char type;
    private boolean isMembers;
    private int defaultInt;
    private String defaultString;

    public ParamDefinition(int id) {
        this.id = id;
        isMembers = true;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                type = (char) buffer.get();
            } else if (opcode == 2) {
                defaultInt = buffer.getInt();
            } else if (opcode == 4) {
                isMembers = false;
            } else if (opcode == 5) {
                defaultString = ByteBufferExt.getString(buffer);
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
