package com.osrsd.cache.util;

import com.osrsd.cache.def.Definition;
import com.osrsd.cache.loader.Loader;
import lombok.Value;

import java.util.List;

@Value
public class Serializable {
    Loader loader;
    List<Definition> definitions;
    String path;
}
