package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.Library;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.SoundEffectDefinition;
import com.osrsd.cache.provider.SoundEffectProvider;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SoundEffectLoader implements Loader {

    @Override
    public Serializable load(Library library) {
        Index index = library.index(Indexes.SOUND_EFFECTS);

        List<Definition> definitions = new ArrayList<>(index.archives().length);
        Arrays.stream(index.archives()).forEach(archive -> {
            byte[] data = Objects.requireNonNull(archive.first()).getData();
            if (data != null) {
                definitions.add(SoundEffectProvider.decode(ByteBuffer.wrap(data), new SoundEffectDefinition(archive.getId())));
            }
        });
        return new Serializable(this, definitions, "/soundeffects");
    }

}
