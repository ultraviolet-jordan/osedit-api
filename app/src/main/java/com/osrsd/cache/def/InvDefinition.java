package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

@Data
public final class InvDefinition implements Definition {

    private int id;
    private int size;

    public InvDefinition(int id) {
        this.id = id;
    }

}
