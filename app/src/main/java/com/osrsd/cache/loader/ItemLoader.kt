package com.osrsd.cache.loader

import com.displee.cache.index.archive.Archive
import com.osrsd.cache.Configs
import com.osrsd.cache.Indexes
import com.osrsd.cache.Library
import com.osrsd.cache.provider.ItemProvider
import com.osrsd.cache.util.Serializable
import com.osrsd.spring.domain.ItemDefinition
import java.nio.ByteBuffer

class ItemLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.ITEMS)!!
        val definitions = archive.fileIds().map { member ->
            ItemProvider.decode(ByteBuffer.wrap(archive.file(member)?.data), ItemDefinition(member))
        }
        return Serializable(this, definitions, "/items")
    }

}