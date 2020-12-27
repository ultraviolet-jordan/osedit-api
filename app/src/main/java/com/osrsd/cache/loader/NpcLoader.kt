package com.osrsd.cache.loader

import com.displee.cache.index.archive.Archive
import com.osrsd.cache.Configs
import com.osrsd.cache.Indexes
import com.osrsd.cache.Library
import com.osrsd.cache.provider.NpcProvider
import com.osrsd.cache.util.Serializable
import com.osrsd.spring.domain.NpcDefinition
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