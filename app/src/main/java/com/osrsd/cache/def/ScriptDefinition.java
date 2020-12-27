package com.osrsd.cache.def;

import com.osrsd.spring.domain.Definition;
import lombok.Data;

import java.util.Map;

@Data
public final class ScriptDefinition implements Definition {

    private int id;
    private int[] instructions;
    private int[] intOperands;
    private String[] stringOperands;
    private int intStackCount;
    private int stringStackCount;
    private int localIntCount;
    private int localStringCount;
    private Map<Integer, Integer>[] switches;

    public ScriptDefinition(int id) {
        this.id = id;
    }

}
