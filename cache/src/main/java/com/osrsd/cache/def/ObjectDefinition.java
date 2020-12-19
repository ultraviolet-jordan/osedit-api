package com.osrsd.cache.def;

import com.osrsd.cache.util.ByteBufferExt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Data
@Slf4j
public final class ObjectDefinition implements Definition {

    private int id;
    private short[] retextureToFind;
    private int decorDisplacement;
    private boolean isHollow;
    private String name;
    private int[] objectModels;
    private int[] objectTypes;
    private short[] recolorToFind;
    private int mapAreaId;
    private short[] textureToReplace;
    private int sizeX;
    private int sizeY;
    private int anInt2083;
    private int[] anIntArray2084;
    private int offsetX;
    private boolean mergeNormals;
    private int wallOrDoor;
    private int animationID;
    private int varbitID;
    private int ambient;
    private int contrast;
    private String[] actions;
    private int interactType;
    private int mapSceneID;
    private int blockingMask;
    private short[] recolorToReplace;
    private boolean shadow;
    private int modelSizeX;
    private int modelSizeHeight;
    private int modelSizeY;
    private int offsetHeight;
    private int offsetY;
    private boolean obstructsGround;
    private int contouredGround;
    private int supportsItems;
    private int[] configChangeDest;
    private boolean isRotated;
    private int varpID;
    private int ambientSoundId;
    private boolean aBool2111;
    private int anInt2112;
    private int anInt2113;
    private boolean blocksProjectile;
    private Map<Integer, Object> params;

    public ObjectDefinition(int id) {
        this.id = id;
        decorDisplacement = 16;
        isHollow = false;
        name = "null";
        mapAreaId = -1;
        sizeX = 1;
        sizeY = 1;
        anInt2083 = 0;
        offsetX = 0;
        mergeNormals = false;
        wallOrDoor = -1;
        animationID = -1;
        varbitID = -1;
        ambient = 0;
        contrast = 0;
        actions = new String[5];
        interactType = 2;
        mapSceneID = -1;
        blockingMask = 0;
        shadow = true;
        modelSizeX = 128;
        modelSizeHeight = 128;
        modelSizeY = 128;
        offsetHeight = 0;
        offsetY = 0;
        obstructsGround = false;
        contouredGround = -1;
        supportsItems = -1;
        isRotated = false;
        varpID = -1;
        ambientSoundId = -1;
        aBool2111 = false;
        anInt2112 = 0;
        anInt2113 = 0;
        blocksProjectile = true;
        params = new HashMap<>();
    }

    @Override
    public void decode(ByteBuffer buffer) {
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
                    this.objectTypes = objectTypes;
                    this.objectModels = objectModels;
                }
            } else if (opcode == 2) {
                name = ByteBufferExt.getString(buffer);
            } else if (opcode == 5) {
                int length = buffer.get() & 0xff;
                if (length > 0) {
                    objectTypes = null;
                    objectModels = IntStream.range(0, length).map(index -> buffer.getShort() & 0xffff).toArray();
                }
            } else if (opcode == 14) {
                sizeX = buffer.get() & 0xff;
            } else if (opcode == 15) {
                sizeY = buffer.get() & 0xff;
            } else if (opcode == 17) {
                interactType = 0;
                blocksProjectile = false;
            } else if (opcode == 18) {
                blocksProjectile = false;
            } else if (opcode == 19) {
                wallOrDoor = buffer.get() & 0xff;
            } else if (opcode == 21) {
                contouredGround = 0;
            } else if (opcode == 22) {
                mergeNormals = true;
            } else if (opcode == 23) {
                aBool2111 = true;
            } else if (opcode == 24) {
                animationID = buffer.getShort() & 0xffff;
                if (animationID == 0xffff) {
                    animationID = -1;
                }
            } else if (opcode == 27) {
                interactType = 1;
            } else if (opcode == 28) {
                decorDisplacement = buffer.get() & 0xff;
            } else if (opcode == 29) {
                ambient = buffer.get();
            } else if (opcode == 39) {
                contrast = buffer.get() * 25;
            } else if (opcode >= 30 && opcode < 35) {
                String[] actions = this.actions;
                actions[opcode - 30] = ByteBufferExt.getString(buffer);
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
                this.recolorToFind = recolorToFind;
                this.recolorToReplace = recolorToReplace;
            } else if (opcode == 41) {
                int length = buffer.get() & 0xff;
                short[] retextureToFind = new short[length];
                short[] textureToReplace = new short[length];
                IntStream.range(0, length).forEach(index -> {
                    retextureToFind[index] = buffer.getShort();
                    textureToReplace[index] = buffer.getShort();
                });
                this.retextureToFind = retextureToFind;
                this.textureToReplace = textureToReplace;
            } else if (opcode == 62) {
                isRotated = true;
            } else if (opcode == 64) {
                shadow = false;
            } else if (opcode == 65) {
                modelSizeX = buffer.getShort() & 0xffff;
            } else if (opcode == 66) {
                modelSizeHeight = buffer.getShort() & 0xffff;
            } else if (opcode == 67) {
                modelSizeY = buffer.getShort() & 0xffff;
            } else if (opcode == 68) {
                mapSceneID = buffer.getShort() & 0xffff;
            } else if (opcode == 69) {
                blockingMask = buffer.get();
            } else if (opcode == 70) {
                offsetX = buffer.getShort() & 0xffff;
            } else if (opcode == 71) {
                offsetHeight = buffer.getShort() & 0xffff;
            } else if (opcode == 72) {
                offsetY = buffer.getShort() & 0xffff;
            } else if (opcode == 73) {
                obstructsGround = true;
            } else if (opcode == 74) {
                isHollow = true;
            } else if (opcode == 75) {
                supportsItems = buffer.get() & 0xff;
            } else if (opcode == 77) {
                int varpID = buffer.getShort() & 0xffff;
                if (varpID == 0xFFFF) {
                    varpID = -1;
                }
                this.varbitID = varpID;
                int configId = buffer.getShort() & 0xffff;
                if (configId == 0xFFFF) {
                    configId = -1;
                }
                this.varpID = configId;
                int length = buffer.get() & 0xff;
                int[] configChangeDest = new int[length + 2];
                IntStream.rangeClosed(0, length).forEach(index -> {
                    configChangeDest[index] = buffer.getShort() & 0xffff;
                    if (0xffff == configChangeDest[index]) {
                        configChangeDest[index] = -1;
                    }
                });
                configChangeDest[length + 1] = -1;
                this.configChangeDest = configChangeDest;
            } else if (opcode == 78) {
                ambientSoundId = buffer.getShort() & 0xffff;
                anInt2083 = buffer.get() & 0xff;
            } else if (opcode == 79) {
                anInt2112 = buffer.getShort() & 0xffff;
                anInt2113 = buffer.getShort() & 0xffff;
                anInt2083 = buffer.get() & 0xff;
                int length = buffer.get() & 0xff;
                anIntArray2084 = IntStream.range(0, length).map(index -> buffer.getShort() & 0xffff).toArray();
            } else if (opcode == 81) {
                contouredGround = (buffer.get() & 0xff) * 256;
            } else if (opcode == 82) {
                mapAreaId = buffer.getShort() & 0xffff;
            } else if (opcode == 92) {
                int varpID = buffer.getShort() & 0xffff;
                if (varpID == 0xffff) {
                    varpID = -1;
                }
                this.varbitID = varpID;
                int configId = buffer.getShort() & 0xffff;
                if (configId == 0xffff) {
                    configId = -1;
                }
                this.varpID = configId;
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
                this.configChangeDest = configChangeDest;
            } else if (opcode == 249) {
                int length = buffer.get() & 0xff;
                Map<Integer, Object> params = new HashMap<>(length);
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
                this.params = params;
            } else {
                log.warn(String.format("Unhandled definition opcode with id: %s.", opcode));
            }
        } while (true);
        post();
    }

    private void post() {
        if (wallOrDoor == -1) {
            wallOrDoor = 0;
            if (objectModels != null && (objectTypes == null || objectTypes[0] == 10)) {
                wallOrDoor = 1;
            }
            if (IntStream.range(0, 5).anyMatch(index -> actions[index] != null)) {
                wallOrDoor = 1;
            }
        }
        if (supportsItems == -1) {
            supportsItems = interactType != 0 ? 1 : 0;
        }
    }

}
