package com.osrsd.cache.def;

import lombok.Data;

import java.util.HashMap;

@Data
public final class EnumDefinition implements Definition {

    private int id;
    private char keyType;
    private char valType;
    private String defaultString;
    private int defaultInt;
    private HashMap<Long, Object> values;

    public EnumDefinition(int id) {
        this.id = id;
        defaultString = "null";
        values = new HashMap<>();
    }

}
