package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.SequenceProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.SequenceDefinition
import java.nio.ByteBuffer

class SequenceLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.SEQUENCES)!!
        val definitions = archive.fileIds().map {
            SequenceProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), SequenceDefinition(it))
        }
        return Serializable(this, definitions, "/sequences")
    }

}