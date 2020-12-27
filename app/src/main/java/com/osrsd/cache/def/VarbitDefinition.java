package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

@Data
public final class VarbitDefinition implements Definition {

    private int id;
    private int index;
    private int leastSignificantBit;
    private int mostSignificantBit;

    public VarbitDefinition(int id) {
        this.id = id;
    }

}
