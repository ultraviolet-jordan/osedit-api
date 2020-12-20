package com.osrsd.cache.def;

import lombok.Data;

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

}
