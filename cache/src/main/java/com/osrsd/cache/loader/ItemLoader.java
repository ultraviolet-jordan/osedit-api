package com.osrsd.cache.loader;

import com.displee.cache.CacheLibrary;
import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.Archives;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.ItemDefinition;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLoader implements Loader {

    @Override
    public Serializable load(CacheLibrary cache) {
        Index index = cache.index(Indexes.CONFIG);
        Archive archive = index.archive(Archives.ITEMS);

        assert archive != null;
        List<Definition> definitions = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = cache.data(index.getId(), archive.getId(), fileId);
            if (data != null) {
                ItemDefinition definition = new ItemDefinition(fileId);
                definition.decode(ByteBuffer.wrap(data));
                definitions.add(definition);
            }
        });
        return new Serializable(this, definitions, "/items");
    }

}
