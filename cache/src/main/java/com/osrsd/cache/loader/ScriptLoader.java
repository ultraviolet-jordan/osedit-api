package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.Library;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.ScriptDefinition;
import com.osrsd.cache.provider.ScriptProvider;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptLoader implements Loader {

    @Override
    public Serializable load(Library library) {
        Index index = library.index(Indexes.SCRIPTS);

        List<Definition> definitions = new ArrayList<>(index.archives().length);
        Arrays.stream(index.archives()).forEach(archive -> {
            byte[] data = library.data(index.getId(), archive.getId(), 0);
            if (data != null) {
                definitions.add(ScriptProvider.decode(ByteBuffer.wrap(data), new ScriptDefinition(archive.getId())));
            }
        });
        return new Serializable(this, definitions, "/scripts");
    }

}
