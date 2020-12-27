package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.VarbitProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.VarbitDefinition
import java.nio.ByteBuffer

class VarbitLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.VARBITS)!!
        val definitions = archive.fileIds().map {
            VarbitProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), VarbitDefinition(it))
        }
        return Serializable(this, definitions, "/varbits")
    }

}