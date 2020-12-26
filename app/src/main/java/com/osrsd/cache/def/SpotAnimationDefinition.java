package com.osrsd.cache.def;

import lombok.Data;

@Data
public final class SpotAnimationDefinition implements Definition {

    private final int id;
    private int rotation;
    private short[] textureToReplace;
    private short[] textureToFind;
    private int resizeY;
    private int animationId;
    private short[] recolorToFind;
    private short[] recolorToReplace;
    private int resizeX;
    private int modelId;
    private int ambient;
    private int contrast;

    public SpotAnimationDefinition(int id) {
        this.id = id;
        rotation = 0;
        resizeY = 128;
        animationId = -1;
        resizeX = 128;
        ambient = 0;
        contrast = 0;
    }

}
