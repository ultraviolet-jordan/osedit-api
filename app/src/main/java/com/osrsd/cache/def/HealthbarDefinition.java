package com.osrsd.cache.def;

import lombok.Data;

@Data
public final class HealthbarDefinition implements Definition {

    private int id;
    private int field3276;
    private int field3277;
    private int field3278;
    private int field3283;
    private int field3272;
    private int field3275;
    private int healthBarFrontSpriteId;
    private int healthBarBackSpriteId;
    private int healthScale;
    private int healthBarPadding;

    public HealthbarDefinition(int id) {
        this.id = id;
        field3277 = 255;
        field3278 = 255;
        field3283 = -1;
        field3272 = 1;
        field3275 = 70;
        healthBarFrontSpriteId = -1;
        healthBarBackSpriteId = -1;
        healthScale = 30;
        healthBarPadding = 0;
    }

}
