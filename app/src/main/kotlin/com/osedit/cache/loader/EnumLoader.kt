package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.EnumProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.EnumDefinition
import java.nio.ByteBuffer

class EnumLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.ENUMS)!!
        val definitions = archive.fileIds().map {
            EnumProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), EnumDefinition(it))
        }
        return Serializable(this, definitions, "/enums")
    }

}