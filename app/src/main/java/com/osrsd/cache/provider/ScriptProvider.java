package com.osrsd.cache.provider;

import com.osrsd.cache.def.ScriptDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import com.osrsd.spring.domain.Definition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public final class ScriptProvider {

    static final int SCONST = 3;
    static final int RETURN = 21;
    static final int POP_INT = 38;
    static final int POP_STRING = 39;

    public static Definition decode(ByteBuffer buffer, ScriptDefinition definition) {
        buffer.position(buffer.limit() - 2);
        int switchLength = buffer.getShort() & 0xffff;
        // 2 for switchLength + the switch data + 12 for the param/vars/stack data
        int endIdx = buffer.limit() - 2 - switchLength - 12;
        buffer.position(endIdx);
        int numOpcodes = buffer.getInt();
        int localIntCount = buffer.getShort() & 0xffff;
        int localStringCount = buffer.getShort() & 0xffff;
        int intStackCount = buffer.getShort() & 0xffff;
        int stringStackCount = buffer.getShort() & 0xffff;
        int numSwitches = buffer.get() & 0xff;
        if (numSwitches > 0) {
            Map<Integer, Integer>[] switches = new Map[numSwitches];
            definition.setSwitches(switches);
            for (int i = 0; i < numSwitches; ++i) {
                switches[i] = new HashMap<>();
                int count = buffer.getShort() & 0xffff;
                while (count-- > 0) {
                    int key = buffer.getInt(); // int from stack is compared to this
                    int pcOffset = buffer.getInt(); // pc jumps by this
                    switches[i].put(key, pcOffset);
                }
            }
        }
        definition.setLocalIntCount(localIntCount);
        definition.setLocalStringCount(localStringCount);
        definition.setIntStackCount(intStackCount);
        definition.setStringStackCount(stringStackCount);
        buffer.position(0);
        ByteBufferExt.INSTANCE.getStringOrNull(buffer);
        int[] instructions = new int[numOpcodes];
        int[] intOperands = new int[numOpcodes];
        String[] stringOperands = new String[numOpcodes];
        definition.setInstructions(instructions);
        definition.setIntOperands(intOperands);
        definition.setStringOperands(stringOperands);
        int opcode;
        for (int i = 0; buffer.position() < endIdx; instructions[i++] = opcode) {
            opcode = buffer.getShort() & 0xffff;
            if (opcode == SCONST) {
                stringOperands[i] = ByteBufferExt.INSTANCE.getString(buffer);
            } else if (opcode < 100 && opcode != RETURN && opcode != POP_INT && opcode != POP_STRING) {
                intOperands[i] = buffer.getInt();
            } else {
                intOperands[i] = buffer.get() & 0xff;
            }
        }
        return definition;
    }

}
