package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.Configs;
import com.osrsd.cache.def.InvDefinition;
import com.osrsd.cache.provider.InvProvider;
import com.osrsd.cache.util.Serializable;
import com.osrsd.spring.domain.Definition;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InvLoader implements Loader {

    @Override
    public Serializable load() {
        Index index = null;//Library.INSTANCE.index(Indexes.CONFIG);
        Archive archive = index.archive(Configs.INVS);

        assert archive != null;
        List<Definition> definitions = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = Objects.requireNonNull(archive.file(fileId)).getData();
            if (data != null) {
                definitions.add(InvProvider.decode(ByteBuffer.wrap(data), new InvDefinition(fileId)));
            }
        });
        return new Serializable(this, definitions, "/invs");
    }

}
