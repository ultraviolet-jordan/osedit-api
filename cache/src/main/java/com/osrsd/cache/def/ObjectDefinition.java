package com.osrsd.cache.def;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
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

}
