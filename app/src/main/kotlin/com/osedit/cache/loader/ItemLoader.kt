package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.ItemProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.ItemDefinition
import java.nio.ByteBuffer

class ItemLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.ITEMS)!!
        val definitions = archive.fileIds().map {
            ItemProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), ItemDefinition(it))
        }
        return Serializable(this, definitions, "/items")
    }

}