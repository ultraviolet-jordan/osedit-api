package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

@Data
public final class NpcDefinition implements Definition {

    private int id;
    private String name;
    private int size;
    private int[] models;
    private int[] chatheadModels;
    private int standingAnimation;
    private int rotateLeftAnimation;
    private int rotateRightAnimation;
    private int walkingAnimation;
    private int rotate180Animation;
    private int rotate90RightAnimation;
    private int rotate90LeftAnimation;
    private short[] recolorToFind;
    private short[] recolorToReplace;
    private short[] retextureToFind;
    private short[] retextureToReplace;
    private String[] actions;
    private boolean isMinimapVisible;
    private int combatLevel;
    private int widthScale;
    private int heightScale;
    private boolean hasRenderPriority;
    private int ambient;
    private int contrast;
    private int headIcon;
    private int rotationSpeed;
    private int[] configs;
    private int varbitId;
    private int varpIndex;
    private boolean isInteractable;
    private boolean rotationFlag;
    private boolean isPet;
    private Map<Integer, Object> params;

    public NpcDefinition(int id) {
        this.id = id;
        name = "null";
        size = 1;
        standingAnimation = -1;
        rotateLeftAnimation = -1;
        rotateRightAnimation = -1;
        walkingAnimation = -1;
        rotate180Animation = -1;
        rotate90RightAnimation = -1;
        rotate90LeftAnimation = -1;
        actions = new String[5];
        isMinimapVisible = true;
        combatLevel = -1;
        widthScale = 128;
        heightScale = 128;
        headIcon = -1;
        rotationSpeed = 32;
        varbitId = -1;
        varpIndex = -1;
        isInteractable = true;
        rotationFlag = true;
        params = new HashMap<>();
    }

    @Override
    public void decode(ByteBuffer buffer) {
        while (true) {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            int length;
            int index;
            if (opcode == 1) {
                length = buffer.get() & 0xff;
                models = new int[length];
                for (index = 0; index < length; ++index) {
                    models[index] = buffer.getShort() & 0xffff;
                }
            } else if (opcode == 2) {
                name = ByteBufferExt.getString(buffer);
            } else if (opcode == 12) {
                size = buffer.get() & 0xff;
            } else if (opcode == 13) {
                standingAnimation = buffer.getShort() & 0xffff;
            } else if (opcode == 14) {
                walkingAnimation = buffer.getShort() & 0xffff;
            } else if (opcode == 15) {
                rotateLeftAnimation = buffer.getShort() & 0xffff;
            } else if (opcode == 16) {
                rotateRightAnimation = buffer.getShort() & 0xffff;
            } else if (opcode == 17) {
                walkingAnimation = buffer.getShort() & 0xffff;
                rotate180Animation = buffer.getShort() & 0xffff;
                rotate90RightAnimation = buffer.getShort() & 0xffff;
                rotate90LeftAnimation = buffer.getShort() & 0xffff;
            } else if (opcode >= 30 && opcode < 35) {
                actions[opcode - 30] = ByteBufferExt.getString(buffer);
                if (actions[opcode - 30].equalsIgnoreCase("Hidden")) {
                    actions[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                length = buffer.get() & 0xff;
                recolorToFind = new short[length];
                recolorToReplace = new short[length];
                for (index = 0; index < length; ++index) {
                    recolorToFind[index] = (short) (buffer.getShort() & 0xffff);
                    recolorToReplace[index] = (short) (buffer.getShort() & 0xffff);
                }
            } else if (opcode == 41) {
                length = buffer.get() & 0xff;
                retextureToFind = new short[length];
                retextureToReplace = new short[length];
                for (index = 0; index < length; ++index) {
                    retextureToFind[index] = (short) (buffer.getShort() & 0xffff);
                    retextureToReplace[index] = (short) (buffer.getShort() & 0xffff);
                }
            } else if (opcode == 60) {
                length = buffer.get() & 0xff;
                chatheadModels = new int[length];
                for (index = 0; index < length; ++index) {
                    chatheadModels[index] = buffer.getShort() & 0xffff;
                }
            } else if (opcode == 93) {
                isMinimapVisible = false;
            } else if (opcode == 95) {
                combatLevel = buffer.getShort() & 0xffff;
            } else if (opcode == 97) {
                widthScale = buffer.getShort() & 0xffff;
            } else if (opcode == 98) {
                heightScale = buffer.getShort() & 0xffff;
            } else if (opcode == 99) {
                hasRenderPriority = true;
            } else if (opcode == 100) {
                ambient = buffer.get();
            } else if (opcode == 101) {
                contrast = buffer.get();
            } else if (opcode == 102) {
                headIcon = buffer.getShort() & 0xffff;
            } else if (opcode == 103) {
                rotationSpeed = buffer.getShort() & 0xffff;
            } else if (opcode == 106) {
                varbitId = buffer.getShort() & 0xffff;
                if (varbitId == 65535) {
                    varbitId = -1;
                }
                varpIndex = buffer.getShort() & 0xffff;
                if (varpIndex == 65535) {
                    varpIndex = -1;
                }
                length = buffer.get() & 0xff;
                configs = new int[length + 2];
                for (index = 0; index <= length; ++index) {
                    configs[index] = buffer.getShort() & 0xffff;
                    if (configs[index] == '\uffff') {
                        configs[index] = -1;
                    }
                }
                configs[length + 1] = -1;
            } else if (opcode == 107) {
                isInteractable = false;
            } else if (opcode == 109) {
                rotationFlag = false;
            } else if (opcode == 111) {
                isPet = true;
            } else if (opcode == 118) {
                varbitId = buffer.getShort() & 0xffff;
                if (varbitId == 65535) {
                    varbitId = -1;
                }
                varpIndex = buffer.getShort() & 0xffff;
                if (varpIndex == 65535) {
                    varpIndex = -1;
                }
                int var = buffer.getShort() & 0xffff;
                if (var == 0xFFFF) {
                    var = -1;
                }
                length = buffer.get() & 0xff;
                configs = new int[length + 2];
                for (index = 0; index <= length; ++index) {
                    configs[index] = buffer.getShort() & 0xffff;
                    if (configs[index] == '\uffff') {
                        configs[index] = -1;
                    }
                }
                configs[length + 1] = var;
            } else if (opcode == 249) {
                length = buffer.get() & 0xff;
                params = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    boolean isString = (buffer.get() & 0xff) == 1;
                    int key = ByteBufferExt.getMedium(buffer);
                    Object value;
                    if (isString) {
                        value = ByteBufferExt.getString(buffer);
                    } else {
                        value = buffer.getInt();
                    }
                    params.put(key, value);
                }
            }
        }
    }

}
