package com.osrsd.cache.loader;

import com.osrsd.cache.Library;
import com.osrsd.cache.util.Serializable;

public interface Loader {
    Serializable load(Library library);
}
