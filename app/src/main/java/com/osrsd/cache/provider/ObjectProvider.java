package com.osrsd.cache.provider;

import com.osrsd.cache.def.ObjectDefinition;
import com.osrsd.cache.util.ByteBufferExt;
import com.osrsd.spring.domain.Definition;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.stream.IntStream;

@Slf4j
public final class ObjectProvider {

    public static Definition decode(ByteBuffer buffer, ObjectDefinition definition) {
        do {
            int opcode = buffer.get() & 0xff;
            if (opcode == 0) {
                break;
            }

            if (opcode == 1) {
                int length = buffer.get() & 0xff;
                if (length > 0) {
                    int[] objectTypes = new int[length];
                    int[] objectModels = new int[length];
                    IntStream.range(0, length).forEach(index -> {
                        objectModels[index] = buffer.getShort() & 0xffff;
                        objectTypes[index] = buffer.get() & 0xff;
                    });
                    definition.setObjectTypes(objectTypes);
                    definition.setObjectModels(objectModels);
                }
            } else if (opcode == 2) {
                definition.setName(ByteBufferExt.INSTANCE.getString(buffer));
            } else if (opcode == 5) {
                int length = buffer.get() & 0xff;
                if (length > 0) {
                    definition.setObjectTypes(null);
                    definition.setObjectModels(IntStream.range(0, length).map(index -> buffer.getShort() & 0xffff).toArray());
                }
            } else if (opcode == 14) {
                definition.setSizeX(buffer.get() & 0xff);
            } else if (opcode == 15) {
                definition.setSizeY(buffer.get() & 0xff);
            } else if (opcode == 17) {
                definition.setInteractType(0);
                definition.setBlocksProjectile(false);
            } else if (opcode == 18) {
                definition.setBlocksProjectile(false);
            } else if (opcode == 19) {
                definition.setWallOrDoor(buffer.get() & 0xff);
            } else if (opcode == 21) {
                definition.setContouredGround(0);
            } else if (opcode == 22) {
                definition.setMergeNormals(true);
            } else if (opcode == 23) {
                definition.setABool2111(true);
            } else if (opcode == 24) {
                definition.setAnimationID(buffer.getShort() & 0xffff);
                if (definition.getAnimationID() == 0xffff) {
                    definition.setAnimationID(-1);
                }
            } else if (opcode == 27) {
                definition.setInteractType(1);
            } else if (opcode == 28) {
                definition.setDecorDisplacement(buffer.get() & 0xff);
            } else if (opcode == 29) {
                definition.setAmbient(buffer.get());
            } else if (opcode == 39) {
                definition.setContrast(buffer.get() * 25);
            } else if (opcode >= 30 && opcode < 35) {
                String[] actions = definition.getActions();
                actions[opcode - 30] = ByteBufferExt.INSTANCE.getString(buffer);
                if (actions[opcode - 30].equalsIgnoreCase("Hidden")) {
                    actions[opcode - 30] = null;
                }
            } else if (opcode == 40) {
                int length = buffer.get() & 0xff;
                short[] recolorToFind = new short[length];
                short[] recolorToReplace = new short[length];
                IntStream.range(0, length).forEach(index -> {
                    recolorToFind[index] = buffer.getShort();
                    recolorToReplace[index] = buffer.getShort();
                });
                definition.setRecolorToFind(recolorToFind);
                definition.setRecolorToReplace(recolorToReplace);
            } else if (opcode == 41) {
                int length = buffer.get() & 0xff;
                short[] retextureToFind = new short[length];
                short[] textureToReplace = new short[length];
                IntStream.range(0, length).forEach(index -> {
                    retextureToFind[index] = buffer.getShort();
                    textureToReplace[index] = buffer.getShort();
                });
                definition.setRetextureToFind(retextureToFind);
                definition.setTextureToReplace(textureToReplace);
            } else if (opcode == 62) {
                definition.setRotated(true);
            } else if (opcode == 64) {
                definition.setShadow(false);
            } else if (opcode == 65) {
                definition.setModelSizeX(buffer.getShort() & 0xffff);
            } else if (opcode == 66) {
                definition.setModelSizeHeight(buffer.getShort() & 0xffff);
            } else if (opcode == 67) {
                definition.setModelSizeY(buffer.getShort() & 0xffff);
            } else if (opcode == 68) {
                definition.setMapSceneID(buffer.getShort() & 0xffff);
            } else if (opcode == 69) {
                definition.setBlockingMask(buffer.get());
            } else if (opcode == 70) {
                definition.setOffsetX(buffer.getShort() & 0xffff);
            } else if (opcode == 71) {
                definition.setOffsetHeight(buffer.getShort() & 0xffff);
            } else if (opcode == 72) {
                definition.setOffsetY(buffer.getShort() & 0xffff);
            } else if (opcode == 73) {
                definition.setObstructsGround(true);
            } else if (opcode == 74) {
                definition.setHollow(true);
            } else if (opcode == 75) {
                definition.setSupportsItems(buffer.get() & 0xff);
            } else if (opcode == 77) {
                int varpID = buffer.getShort() & 0xffff;
                if (varpID == 0xFFFF) {
                    varpID = -1;
                }
                definition.setVarbitID(varpID);
                int configId = buffer.getShort() & 0xffff;
                if (configId == 0xFFFF) {
                    configId = -1;
                }
                definition.setVarpID(configId);
                int length = buffer.get() & 0xff;
                int[] configChangeDest = new int[length + 2];
                IntStream.rangeClosed(0, length).forEach(index -> {
                    configChangeDest[index] = buffer.getShort() & 0xffff;
                    if (0xffff == configChangeDest[index]) {
                        configChangeDest[index] = -1;
                    }
                });
                configChangeDest[length + 1] = -1;
                definition.setConfigChangeDest(configChangeDest);
            } else if (opcode == 78) {
                definition.setAmbientSoundId(buffer.getShort() & 0xffff);
                definition.setAnInt2083(buffer.get() & 0xff);
            } else if (opcode == 79) {
                definition.setAnInt2112(buffer.getShort() & 0xffff);
                definition.setAnInt2113(buffer.getShort() & 0xffff);
                definition.setAnInt2083(buffer.get() & 0xff);
                int length = buffer.get() & 0xff;
                definition.setAnIntArray2084(IntStream.range(0, length).map(index -> buffer.getShort() & 0xffff).toArray());
            } else if (opcode == 81) {
                definition.setContouredGround((buffer.get() & 0xff) * 256);
            } else if (opcode == 82) {
                definition.setMapAreaId(buffer.getShort() & 0xffff);
            } else if (opcode == 92) {
                int varpID = buffer.getShort() & 0xffff;
                if (varpID == 0xffff) {
                    varpID = -1;
                }
                definition.setVarbitID(varpID);
                int configId = buffer.getShort() & 0xffff;
                if (configId == 0xffff) {
                    configId = -1;
                }
                definition.setVarpID(configId);
                int var = buffer.getShort() & 0xffff;
                if (var == 0xffff) {
                    var = -1;
                }
                int length = buffer.get() & 0xff;
                int[] configChangeDest = new int[length + 2];
                IntStream.rangeClosed(0, length).forEach(index -> {
                    configChangeDest[index] = buffer.getShort() & 0xffff;
                    if (0xffff == configChangeDest[index]) {
                        configChangeDest[index] = -1;
                    }
                });
                configChangeDest[length + 1] = var;
                definition.setConfigChangeDest(configChangeDest);
            } else if (opcode == 249) {
                int length = buffer.get() & 0xff;
                for (int i = 0; i < length; i++) {
                    boolean isString = (buffer.get() & 0xff) == 1;
                    int key = ByteBufferExt.INSTANCE.getMedium(buffer);
                    Object value;
                    if (isString) {
                        value = ByteBufferExt.INSTANCE.getString(buffer);
                    } else {
                        value = buffer.getInt();
                    }
                    definition.getParams().put(key, value);
                }
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        post(definition);
        return definition;
    }

    private static void post(ObjectDefinition definition) {
        if (definition.getWallOrDoor() == -1) {
            definition.setWallOrDoor(0);
            if (definition.getObjectModels() != null && (definition.getObjectTypes() == null || definition.getObjectTypes()[0] == 10)) {
                definition.setWallOrDoor(1);
            }
            if (IntStream.range(0, 5).anyMatch(index -> definition.getActions()[index] != null)) {
                definition.setWallOrDoor(1);
            }
        }
        if (definition.getSupportsItems() == -1) {
            definition.setSupportsItems(definition.getInteractType() != 0 ? 1 : 0);
        }
    }

}
