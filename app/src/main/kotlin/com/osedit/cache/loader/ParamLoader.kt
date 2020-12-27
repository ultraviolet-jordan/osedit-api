package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.ParamProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.ParamDefinition
import java.nio.ByteBuffer

class ParamLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.PARAMS)!!
        val definitions = archive.fileIds().map {
            ParamProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), ParamDefinition(it))
        }
        return Serializable(this, definitions, "/params")
    }

}