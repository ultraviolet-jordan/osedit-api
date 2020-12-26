package com.osrsd.cache.provider;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.NpcDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public final class NpcProvider {

    public static Definition decode(ByteBuffer buffer, NpcDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            int length;
            int index;
            if (opcode == 1) {
                length = buffer.get() & 0xff;
                definition.setModels(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getModels()[index] = buffer.getShort() & 0xffff;
                }
            } else if (opcode == 2) {
                definition.setName(ByteBufferExt.getString(buffer));
            } else if (opcode == 12) {
                definition.setSize(buffer.get() & 0xff);
            } else if (opcode == 13) {
                definition.setStandingAnimation(buffer.getShort() & 0xffff);
            } else if (opcode == 14) {
                definition.setWalkingAnimation(buffer.getShort() & 0xffff);
            } else if (opcode == 15) {
                definition.setRotateLeftAnimation(buffer.getShort() & 0xffff);
            } else if (opcode == 16) {
                definition.setRotateRightAnimation(buffer.getShort() & 0xffff);
            } else if (opcode == 17) {
                definition.setWalkingAnimation(buffer.getShort() & 0xffff);
                definition.setRotate180Animation(buffer.getShort() & 0xffff);
                definition.setRotate90RightAnimation(buffer.getShort() & 0xffff);
                definition.setRotate90LeftAnimation(buffer.getShort() & 0xffff);
            } else if (opcode >= 30 && opcode < 35) {
                definition.getActions()[opcode - 30] = ByteBufferExt.getString(buffer);
                if (definition.getActions()[opcode - 30].equalsIgnoreCase("Hidden")) {
                    definition.getActions()[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                length = buffer.get() & 0xff;
                definition.setRecolorToFind(new short[length]);
                definition.setRecolorToReplace(new short[length]);
                for (index = 0; index < length; ++index) {
                    definition.getRecolorToFind()[index] = (short) (buffer.getShort() & 0xffff);
                    definition.getRecolorToReplace()[index] = (short) (buffer.getShort() & 0xffff);
                }
            } else if (opcode == 41) {
                length = buffer.get() & 0xff;
                definition.setRetextureToFind(new short[length]);
                definition.setRetextureToReplace(new short[length]);
                for (index = 0; index < length; ++index) {
                    definition.getRetextureToFind()[index] = (short) (buffer.getShort() & 0xffff);
                    definition.getRetextureToReplace()[index] = (short) (buffer.getShort() & 0xffff);
                }
            } else if (opcode == 60) {
                length = buffer.get() & 0xff;
                definition.setChatheadModels(new int[length]);
                for (index = 0; index < length; ++index) {
                    definition.getChatheadModels()[index] = buffer.getShort() & 0xffff;
                }
            } else if (opcode == 93) {
                definition.setMinimapVisible(false);
            } else if (opcode == 95) {
                definition.setCombatLevel(buffer.getShort() & 0xffff);
            } else if (opcode == 97) {
                definition.setWidthScale(buffer.getShort() & 0xffff);
            } else if (opcode == 98) {
                definition.setHeightScale(buffer.getShort() & 0xffff);
            } else if (opcode == 99) {
                definition.setHasRenderPriority(true);
            } else if (opcode == 100) {
                definition.setAmbient(buffer.get());
            } else if (opcode == 101) {
                definition.setContrast(buffer.get());
            } else if (opcode == 102) {
                definition.setHeadIcon(buffer.getShort() & 0xffff);
            } else if (opcode == 103) {
                definition.setRotationSpeed(buffer.getShort() & 0xffff);
            } else if (opcode == 106) {
                definition.setVarbitId(buffer.getShort() & 0xffff);
                if (definition.getVarbitId() == 65535) {
                    definition.setVarbitId(-1);
                }
                definition.setVarpIndex(buffer.getShort() & 0xffff);
                if (definition.getVarpIndex() == 65535) {
                    definition.setVarpIndex(-1);
                }
                length = buffer.get() & 0xff;
                definition.setConfigs(new int[length + 2]);
                for (index = 0; index <= length; ++index) {
                    definition.getConfigs()[index] = buffer.getShort() & 0xffff;
                    if (definition.getConfigs()[index] == '\uffff') {
                        definition.getConfigs()[index] = -1;
                    }
                }
                definition.getConfigs()[length + 1] = -1;
            } else if (opcode == 107) {
                definition.setInteractable(false);
            } else if (opcode == 109) {
                definition.setRotationFlag(false);
            } else if (opcode == 111) {
                definition.setPet(true);
            } else if (opcode == 118) {
                definition.setVarbitId(buffer.getShort() & 0xffff);
                if (definition.getVarbitId() == 65535) {
                    definition.setVarbitId(-1);
                }
                definition.setVarpIndex(buffer.getShort() & 0xffff);
                if (definition.getVarpIndex() == 65535) {
                    definition.setVarpIndex(-1);
                }
                int var = buffer.getShort() & 0xffff;
                if (var == 0xFFFF) {
                    var = -1;
                }
                length = buffer.get() & 0xff;
                definition.setConfigs(new int[length + 2]);
                for (index = 0; index <= length; ++index) {
                    definition.getConfigs()[index] = buffer.getShort() & 0xffff;
                    if (definition.getConfigs()[index] == '\uffff') {
                        definition.getConfigs()[index] = -1;
                    }
                }
                definition.getConfigs()[length + 1] = var;
            } else if (opcode == 249) {
                length = buffer.get() & 0xff;
                for (int i = 0; i < length; i++) {
                    boolean isString = (buffer.get() & 0xff) == 1;
                    int key = ByteBufferExt.getMedium(buffer);
                    Object value;
                    if (isString) {
                        value = ByteBufferExt.getString(buffer);
                    } else {
                        value = buffer.getInt();
                    }
                    definition.getParams().put(key, value);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        return definition;
    }

}
