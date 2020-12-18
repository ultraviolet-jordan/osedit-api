package com.osrsd.cache.loader;

import com.displee.cache.CacheLibrary;
import com.osrsd.cache.util.Serializable;

public interface Loader {
    Serializable load(CacheLibrary cache);
}
