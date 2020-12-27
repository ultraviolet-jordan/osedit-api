package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

@Data
public final class TextureDefinition implements Definition {

    private int id;
    private int[] fileIds;

    public TextureDefinition(int id) {
        this.id = id;
    }

}
