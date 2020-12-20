package com.osrsd.cache;

import com.displee.cache.CacheLibrary;
import lombok.Synchronized;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class Library {

    CacheLibrary cacheLibrary;

    public Library(final String path) {
        this.cacheLibrary = new CacheLibrary(path, false, (progress, message) -> log.info(String.format("%s and %s", progress, message)));
    }

    /**
     * Gets raw byte data from the cache library.
     * This is annotated with {@link Synchronized} due to some sort of problem with multiple threads accessing the library simultaneously.
     *
     * @param index   The index to read from the cache library.
     * @param archive The archive to read from the index.
     * @param fileId  The file to read from the archive.
     * @return The raw byte data.
     */
    @Synchronized
    public byte[] data(final int index, final int archive, final int fileId) {
        return cacheLibrary.data(index, archive, fileId);
    }

}
