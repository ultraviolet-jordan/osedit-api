package com.osrsd.cache.def;

import lombok.Data;

import java.nio.ByteBuffer;
import java.util.Arrays;

@Data
public final class TextureDefinition implements Definition {

    private int id;
    private int[] fileIds;

    public TextureDefinition(int id) {
        this.id = id;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        buffer.getShort();
        buffer.get();
        Arrays.setAll(fileIds = new int[buffer.get() & 0xff], id -> buffer.getShort() & 0xffff);
    }

}
