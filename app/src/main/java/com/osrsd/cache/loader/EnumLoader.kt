package com.osrsd.cache.loader

import com.displee.cache.index.archive.Archive
import com.osrsd.cache.Configs
import com.osrsd.cache.Indexes
import com.osrsd.cache.Library
import com.osrsd.cache.provider.EnumProvider
import com.osrsd.cache.util.Serializable
import com.osrsd.spring.domain.EnumDefinition
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