package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.SequenceDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class SequenceProvider {

    public static Definition decode(ByteBuffer buffer, SequenceDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            int length;
            int index;
            if (opcode == 1) {
                length = buffer.getShort() & 0xffff;
                definition.setFrameLengths(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getFrameLengths()[index] = buffer.getShort() & 0xffff;
                }
                definition.setFrameIDs(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getFrameIDs()[index] = buffer.getShort() & 0xffff;
                }
                for (index = 0; index < length; ++index) {
                    definition.getFrameIDs()[index] += (buffer.getShort() & 0xffff) << 16;
                }
            } else if (opcode == 2) {
                definition.setFrameStep(buffer.getShort() & 0xffff);
            } else if (opcode == 3) {
                length = buffer.get() & 0xff;
                definition.setInterleaveLeave(new int[1 + length]);
                for (index = 0; index < length; ++index) {
                    definition.getInterleaveLeave()[index] = buffer.get() & 0xff;
                }
                definition.getInterleaveLeave()[length] = 9999999;
            } else if (opcode == 4) {
                definition.setStretches(true);
            } else if (opcode == 5) {
                definition.setForcedPriority(buffer.get() & 0xff);
            } else if (opcode == 6) {
                definition.setLeftHandItem(buffer.getShort() & 0xffff);
            } else if (opcode == 7) {
                definition.setRightHandItem(buffer.getShort() & 0xffff);
            } else if (opcode == 8) {
                definition.setMaxLoops(buffer.get() & 0xff);
            } else if (opcode == 9) {
                definition.setPrecedenceAnimating(buffer.get() & 0xff);
            } else if (opcode == 10) {
                definition.setPriority(buffer.get() & 0xff);
            } else if (opcode == 11) {
                definition.setReplyMode(buffer.get() & 0xff);
            } else if (opcode == 12) {
                length = buffer.get() & 0xff;
                definition.setChatFrameIds(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getChatFrameIds()[index] = buffer.getShort() & 0xffff;
                }
                for (index = 0; index < length; ++index) {
                    definition.getChatFrameIds()[index] += (buffer.getShort() & 0xffff) << 16;
                }
            } else if (opcode == 13) {
                length = buffer.get() & 0xff;
                definition.setFrameSounds(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getFrameSounds()[index] = ByteBufferExt.getMedium(buffer);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
