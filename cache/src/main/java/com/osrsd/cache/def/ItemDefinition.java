package com.osrsd.cache.def;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public final class ItemDefinition implements Definition {

    private int id;
    private String name;
    private int resizeX;
    private int resizeY;
    private int resizeZ;
    private int xan2d;
    private int yan2d;
    private int zan2d;
    private int cost;
    private boolean isTradeable;
    private int stackable;
    private int inventoryModel;
    private boolean members;
    private short[] colorFind;
    private short[] colorReplace;
    private short[] textureFind;
    private short[] textureReplace;
    private int zoom2d;
    private int xOffset2d;
    private int yOffset2d;
    private int ambient;
    private int contrast;
    private int[] countCo;
    private int[] countObj;
    private String[] options;
    private String[] interfaceOptions;
    private int maleModel0;
    private int maleModel1;
    private int maleModel2;
    private int maleOffset;
    private int maleHeadModel;
    private int maleHeadModel2;
    private int femaleModel0;
    private int femaleModel1;
    private int femaleModel2;
    private int femaleOffset;
    private int femaleHeadModel;
    private int femaleHeadModel2;
    private int notedID;
    private int notedTemplate;
    private int team;
    private int shiftClickDropIndex;
    private int boughtId;
    private int boughtTemplateId;
    private int placeholderId;
    private int placeholderTemplateId;
    private Map<Integer, Object> params;

    public ItemDefinition(int id) {
        this.id = id;
        name = "null";
        resizeX = 128;
        resizeY = 128;
        resizeZ = 128;
        xan2d = 0;
        yan2d = 0;
        zan2d = 0;
        cost = 1;
        stackable = 0;
        members = false;
        zoom2d = 2000;
        xOffset2d = 0;
        yOffset2d = 0;
        options = new String[]{null, null, "Take", null, null};
        interfaceOptions = new String[]{null, null, null, null, "Drop"};
        maleModel0 = -1;
        maleModel1 = -1;
        maleModel2 = -1;
        maleHeadModel = -1;
        maleHeadModel2 = -1;
        femaleModel0 = -1;
        femaleModel1 = -1;
        femaleModel2 = -1;
        femaleHeadModel = -1;
        femaleHeadModel2 = -1;
        notedID = -1;
        notedTemplate = -1;
        shiftClickDropIndex = -2;
        boughtId = -1;
        boughtTemplateId = -1;
        placeholderId = -1;
        placeholderTemplateId = -1;
        params = new HashMap<>();
    }

}
