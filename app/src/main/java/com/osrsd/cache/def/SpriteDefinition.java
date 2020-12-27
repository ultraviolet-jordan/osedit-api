package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public final class SpriteDefinition implements Definition {

    private int id;
    private int width;
    private int height;
    private BufferedImage[] frames;

    public SpriteDefinition(int id) {
        this.id = id;
    }

}
