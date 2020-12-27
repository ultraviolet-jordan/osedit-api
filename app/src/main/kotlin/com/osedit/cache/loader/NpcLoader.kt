package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.NpcProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.NpcDefinition
import java.nio.ByteBuffer

class NpcLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.NPCS)!!
        val definitions = archive.fileIds().map {
            NpcProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), NpcDefinition(it))
        }
        return Serializable(this, definitions, "/npcs")
    }

}