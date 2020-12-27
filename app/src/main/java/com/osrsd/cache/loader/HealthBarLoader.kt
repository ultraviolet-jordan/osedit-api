package com.osrsd.cache.loader

import com.displee.cache.index.archive.Archive
import com.osrsd.cache.Configs
import com.osrsd.cache.Indexes
import com.osrsd.cache.Library
import com.osrsd.cache.provider.HealthBarProvider
import com.osrsd.cache.util.Serializable
import com.osrsd.spring.domain.HealthBarDefinition
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