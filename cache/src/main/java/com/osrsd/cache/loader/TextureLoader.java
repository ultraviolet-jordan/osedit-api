package com.osrsd.cache.loader;

import com.displee.cache.index.Index;
import com.displee.cache.index.archive.Archive;
import com.osrsd.cache.Indexes;
import com.osrsd.cache.Library;
import com.osrsd.cache.def.Definition;
import com.osrsd.cache.def.SpriteDefinition;
import com.osrsd.cache.def.TextureDefinition;
import com.osrsd.cache.util.Serializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextureLoader implements Loader {

    @Override
    public Serializable load(Library library) {
        Index index = library.getCacheLibrary().index(Indexes.TEXTURES);
        Archive archive = index.archive(0);

        assert archive != null;
        List<TextureDefinition> textures = new ArrayList<>(archive.fileIds().length);
        Arrays.stream(archive.fileIds()).forEach(fileId -> {
            byte[] data = library.data(index.getId(), archive.getId(), fileId);
            if (data != null) {
                TextureDefinition definition = new TextureDefinition(fileId);
                definition.decode(ByteBuffer.wrap(data));
                textures.add(definition);
            }
        });

        Index spriteIndex = library.getCacheLibrary().index(Indexes.SPRITES);
        List<Definition> definitions = new ArrayList<>(textures.size());
        textures.forEach(definition -> {
            Arrays.stream(definition.getFileIds()).forEach(fileId -> {
                byte[] data = library.data(spriteIndex.getId(), fileId, 0);
                if (data != null) {
                    SpriteDefinition spriteDefinition = new SpriteDefinition(fileId);
                    spriteDefinition.decode(ByteBuffer.wrap(data));
                    definitions.add(spriteDefinition);
                }
            });
        });
        return new Serializable(this, definitions, "/textures");
    }

}
