package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.MapProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.MapDefinition
import java.nio.ByteBuffer

class MapLoader : Loader {

    override fun load(): Serializable {
        val index = Library.index(Indexes.MAPS);

        val definitions =  Library.mapArchiveKeys().map {
            val regionX: Int = it.id  shr 8
            val regionY: Int = it.id and 0xFF
            val mapArchiveId: Int = index.archiveId(Library.getMapArchiveName(regionX, regionY))
            val mapArchive: Archive = index.archive(mapArchiveId)!!
            val mapData: ByteBuffer = ByteBuffer.wrap(mapArchive.file(0)?.data)
            val def = MapDefinition(it.id);
            def.regionX = regionX
            def.regionY = regionY
            MapProvider.decode(mapData, def)
        }

        return Serializable(this, definitions, "/maps")
    }
}