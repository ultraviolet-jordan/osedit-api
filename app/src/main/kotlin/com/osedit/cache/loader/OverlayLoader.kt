package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.ItemProvider
import com.osedit.cache.provider.OverlayProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.ItemDefinition
import com.osedit.spring.domain.OverlayDefinition
import java.nio.ByteBuffer

class OverlayLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.OVERLAYS)!!
        val definitions = archive.fileIds().map {
            OverlayProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), OverlayDefinition(it))
        }
        return Serializable(this, definitions, "/overlays")
    }
}