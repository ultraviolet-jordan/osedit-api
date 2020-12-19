package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Data
@Slf4j
public final class SequenceDefinition implements Definition {

    private int id;
    private int[] frameIDs;
    private int[] chatFrameIds;
    private int[] frameLengths;
    private int[] frameSounds;
    private int frameStep;
    private int[] interleaveLeave;
    private boolean stretches;
    private int forcedPriority;
    private int leftHandItem;
    private int rightHandItem;
    private int maxLoops;
    private int precedenceAnimating;
    private int priority;
    private int replyMode;

    public SequenceDefinition(int id) {
        this.id = id;
        frameStep = -1;
        stretches = false;
        forcedPriority = 5;
        leftHandItem = -1;
        rightHandItem = -1;
        maxLoops = 99;
        precedenceAnimating = -1;
        priority = -1;
        replyMode = 2;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            int length;
            int index;
            if (opcode == 1) {
                length = buffer.getShort() & 0xffff;
                frameLengths = new int[length];
                for (index = 0; index < length; ++index) {
                    frameLengths[index] = buffer.getShort() & 0xffff;
                }
                frameIDs = new int[length];
                for (index = 0; index < length; ++index) {
                    frameIDs[index] = buffer.getShort() & 0xffff;
                }
                for (index = 0; index < length; ++index) {
                    frameIDs[index] += (buffer.getShort() & 0xffff) << 16;
                }
            } else if (opcode == 2) {
                frameStep = buffer.getShort() & 0xffff;
            } else if (opcode == 3) {
                length = buffer.get() & 0xff;
                interleaveLeave = new int[1 + length];
                for (index = 0; index < length; ++index) {
                    interleaveLeave[index] = buffer.get() & 0xff;
                }
                interleaveLeave[length] = 9999999;
            } else if (opcode == 4) {
                stretches = true;
            } else if (opcode == 5) {
                forcedPriority = buffer.get() & 0xff;
            } else if (opcode == 6) {
                leftHandItem = buffer.getShort() & 0xffff;
            } else if (opcode == 7) {
                rightHandItem = buffer.getShort() & 0xffff;
            } else if (opcode == 8) {
                maxLoops = buffer.get() & 0xff;
            } else if (opcode == 9) {
                precedenceAnimating = buffer.get() & 0xff;
            } else if (opcode == 10) {
                priority = buffer.get() & 0xff;
            } else if (opcode == 11) {
                replyMode = buffer.get() & 0xff;
            } else if (opcode == 12) {
                length = buffer.get() & 0xff;
                chatFrameIds = new int[length];
                for (index = 0; index < length; ++index) {
                    chatFrameIds[index] = buffer.getShort() & 0xffff;
                }
                for (index = 0; index < length; ++index) {
                    chatFrameIds[index] += (buffer.getShort() & 0xffff) << 16;
                }
            } else if (opcode == 13) {
                length = buffer.get() & 0xff;
                frameSounds = new int[length];
                for (index = 0; index < length; ++index) {
                    frameSounds[index] = ByteBufferExt.getMedium(buffer);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
    }

}
