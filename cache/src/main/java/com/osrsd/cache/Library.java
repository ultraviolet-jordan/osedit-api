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

    @Synchronized
    public byte[] data(final int index, final int archive, final int fileId) {
        return cacheLibrary.data(index, archive, fileId);
    }

}
