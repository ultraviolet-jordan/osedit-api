package com.osrsd.cache.loader;

import com.osrsd.cache.Library;
import com.osrsd.cache.util.Serializable;

public interface Loader {
    /**
     * A loader to wrap decoded cache data into a {@link Serializable} object.
     *
     * @param library The loaded cache library used for reading data.
     * @return The data wrapped into a {@link Serializable} used for printing.
     */
    Serializable load(Library library);
}
