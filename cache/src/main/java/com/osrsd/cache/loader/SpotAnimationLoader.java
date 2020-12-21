package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.Configs;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.Library;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.SpotAnimationDefinition;
import com.osrsd.cache.provider.SpotAnimationProvider;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpotAnimationLoader implements Loader {

    @Override
    public Serializable load(Library library) {
        Index index = library.index(Indexes.CONFIG);
        Archive archive = index.archive(Configs.SPOT_ANIMS);

        assert archive != null;
        List<Definition> definitions = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = Objects.requireNonNull(archive.file(fileId)).getData();
            if (data != null) {
                definitions.add(SpotAnimationProvider.decode(ByteBuffer.wrap(data), new SpotAnimationDefinition(fileId)));
            }
        });
        return new Serializable(this, definitions, "/spotanims");
    }

}
