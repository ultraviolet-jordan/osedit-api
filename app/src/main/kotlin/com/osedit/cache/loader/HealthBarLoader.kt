package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.HealthBarProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.HealthBarDefinition
import java.nio.ByteBuffer

class HealthBarLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.HEALTH_BARS)!!
        val definitions = archive.fileIds().map {
            HealthBarProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), HealthBarDefinition(it))
        }
        return Serializable(this, definitions, "/healthbars")
    }

}