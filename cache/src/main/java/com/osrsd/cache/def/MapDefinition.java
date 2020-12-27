package com.osrsd.cache.def;

public class MapDefinition {

    public static final int X = 64;
    public static final int Y = 64;
    public static final int Z = 4;

    public static class Tile {
        public Integer height;
        public int attrOpcode;
        public byte settings;
        public int overlayId;
        public byte overlayPath;
        public byte overlayRotation;
        public byte underlayId;
    }

    private int regionX;
    private int regionY;
    private Tile[][][] tiles = new Tile[Z][X][Y];
}