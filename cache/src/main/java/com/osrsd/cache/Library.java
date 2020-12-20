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
     * Gets raw byte data from the cache library.
     * This is annotated with {@link Synchronized} due to some sort of problem with multiple threads accessing the library simultaneously.
     *
     * @param indexId   The indexId to read from the cache library.
     * @param archiveId The archiveId to read from the indexId.
     * @param fileId  The file to read from the archiveId.
     * @return The raw byte data.
     */
    @Synchronized
    public byte[] data(final int indexId, final int archiveId, final int fileId) {
        Index index = cacheLibrary.index(indexId);
        index.cache();
        Archive archive = index.archive(archiveId);
        if (archive == null) {
            throw new RuntimeException(String.format("Archive could not be grabbed at %s with id %s.", indexId, archiveId));
        }
        return Objects.requireNonNull(archive.file(fileId)).getData();
    }

    @Synchronized
    public Index index(final int indexId) {
        Index index = cacheLibrary.index(indexId);
        index.cache();
        return index;
    }

}
