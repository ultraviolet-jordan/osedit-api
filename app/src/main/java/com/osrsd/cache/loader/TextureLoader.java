package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.SpriteDefinition;
import com.osrsd.cache.def.TextureDefinition;
import com.osrsd.cache.provider.TextureProvider;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TextureLoader implements Loader {

    @Override
    public Serializable load() {
        Index index = null;//Library.INSTANCE.index(Indexes.TEXTURES);
        Archive archive = index.archive(0);

        assert archive != null;
        List<TextureDefinition> textures = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = Objects.requireNonNull(archive.file(fileId)).getData();
            if (data != null) {
                textures.add((TextureDefinition) TextureProvider.decode(ByteBuffer.wrap(data), new TextureDefinition(fileId)));
            }
        });

        Index spriteIndex = null;//Library.INSTANCE.index(Indexes.SPRITES);
        List<Definition> definitions = new ArrayList<>(textures.size());
        textures.forEach(definition -> Arrays.stream(definition.getFileIds()).forEach(archiveId -> {
            byte[] data = Objects.requireNonNull(Objects.requireNonNull(spriteIndex.archive(archiveId)).first()).getData();
            if (data != null) {
                definitions.add(TextureProvider.decodeAsSprite(ByteBuffer.wrap(data), new SpriteDefinition(archiveId)));
            }
        }));
        return new Serializable(this, definitions, "/textures");
    }

}
