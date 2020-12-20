package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.Configs;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.Library;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.EnumDefinition;
import com.osrsd.cache.def.HealthbarDefinition;
import com.osrsd.cache.provider.EnumProvider;
import com.osrsd.cache.provider.HealthBarProvider;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HealthBarLoader implements Loader {

    @Override
    public Serializable load(Library library) {
        Index index = library.index(Indexes.CONFIG);
        Archive archive = index.archive(Configs.HEALTH_BARS);

        assert archive != null;
        List<Definition> definitions = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = library.data(index.getId(), archive.getId(), fileId);
            if (data != null) {
                definitions.add(HealthBarProvider.decode(ByteBuffer.wrap(data), new HealthbarDefinition(fileId)));
            }
        });
        return new Serializable(this, definitions, "/healthbars");
    }

}
