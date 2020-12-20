package com.osrsd.cache.def;

import lombok.Data;

@Data
public final class ParamDefinition implements Definition {

    private int id;
    private char type;
    private boolean isMembers;
    private int defaultInt;
    private String defaultString;

    public ParamDefinition(int id) {
        this.id = id;
        isMembers = true;
    }

}
