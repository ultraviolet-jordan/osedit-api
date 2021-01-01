package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Library
import com.osedit.spring.domain.Definition
import java.nio.ByteBuffer

object DefinitionLoader {

    /**
     * Loads a cache archive from the index and configId and sends it to a definition-based provider.
     *
     * @param definition The definition.
     * @return A list of definitions.
     */
    fun load(definition: Definition): List<Definition> {
        val archive: Archive = Library.index(definition.indices().index).archive(definition.config().configId)!!
            return archive.fileIds().map {
                definition.decode(ByteBuffer.wrap(archive.file(it)?.data))
       }
    }
}





