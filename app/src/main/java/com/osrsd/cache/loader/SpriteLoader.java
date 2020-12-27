package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.osrsd.cache.def.SpriteDefinition;
import com.osrsd.cache.provider.SpriteProvider;
import com.osrsd.cache.util.Serializable;
import com.osrsd.spring.domain.Definition;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpriteLoader implements Loader {

    @Override
    public Serializable load() {
        Index index = null;//Library.INSTANCE.index(Indexes.SPRITES);

        List<Definition> definitions = new ArrayList<>(index.archives().length);
        Arrays.stream(index.archives()).forEach(archive -> {
            byte[] data = Objects.requireNonNull(archive.first()).getData();//library.data(index.getId(), archive.getId(), 0);
            if (data != null) {
                definitions.add(SpriteProvider.decode(ByteBuffer.wrap(data), new SpriteDefinition(archive.getId())));
            }
        });
        return new Serializable(this, definitions, "/sprites");
    }

}
