package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.osrsd.cache.def.ScriptDefinition;
import com.osrsd.cache.provider.ScriptProvider;
import com.osrsd.cache.util.Serializable;
import com.osrsd.spring.domain.Definition;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScriptLoader implements Loader {

    @Override
    public Serializable load() {
        Index index = null;//Library.INSTANCE.index(Indexes.SCRIPTS);

        List<Definition> definitions = new ArrayList<>(index.archives().length);
        Arrays.stream(index.archives()).forEach(archive -> {
            byte[] data = Objects.requireNonNull(archive.first()).getData();
            if (data != null) {
                definitions.add(ScriptProvider.decode(ByteBuffer.wrap(data), new ScriptDefinition(archive.getId())));
            }
        });
        return new Serializable(this, definitions, "/scripts");
    }

}
