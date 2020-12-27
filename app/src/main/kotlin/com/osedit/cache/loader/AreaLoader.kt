package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.AreaProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.AreaDefinition
import java.nio.ByteBuffer

class AreaLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.AREAS)!!
        val definitions = archive.fileIds().map {
            AreaProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), AreaDefinition(it))
        }
        return Serializable(this, definitions, "/areas")
    }

}