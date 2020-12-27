package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.InvProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.InvDefinition
import java.nio.ByteBuffer

class InvLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.INVS)!!
        val definitions = archive.fileIds().map {
            InvProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), InvDefinition(it))
        }
        return Serializable(this, definitions, "/invs")
    }

}