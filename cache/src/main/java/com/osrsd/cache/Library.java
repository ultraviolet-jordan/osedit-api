package com.osrsd.cache;

import com.displee.cache.CacheLibrary;
import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import lombok.Synchronized;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Value
public class Library {

    CacheLibrary cacheLibrary;

    public Library(final String path) {
        this.cacheLibrary = new CacheLibrary(path, false, (progress, message) -> log.info(String.format("%s and %s", progress, message)));
    }

    /**
     * Grabs a {@link Index} with the following id.
     * This is annotated with {@link Synchronized} due to some sort of problem with multiple threads accessing the library simultaneously.
     *
     * @param indexId The indexId to read from the cache library.
     * @return The index.
     */
    @Synchronized
    public Index index(final int indexId) {
        Index index = cacheLibrary.index(indexId);
        index.cache();
        return index;
    }

}
