package com.osrsd.cache.def;

import lombok.Data;

@Data
public final class AreaDefinition implements Definition {

    private int id;
    private int[] field3292;
    private int spriteId;
    private int field3294;
    private String name;
    private int field3296;
    private int field3297;
    private String[] field3298;
    private int[] field3300;
    private String field3308;
    private byte[] field3309;
    private int field3310;

    public AreaDefinition(int id) {
        this.id = id;
        spriteId = -1;
        field3294 = -1;
        field3297 = -1;
        field3298 = new String[5];
    }

}
