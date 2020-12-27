package com.osedit.cache.loader

import com.displee.cache.index.archive.Archive
import com.osedit.cache.Configs
import com.osedit.cache.Indexes
import com.osedit.cache.Library
import com.osedit.cache.provider.SpotAnimationProvider
import com.osedit.cache.util.Serializable
import com.osedit.spring.domain.SpotAnimationDefinition
import java.nio.ByteBuffer

class SpotAnimationLoader : Loader {

    override fun load(): Serializable {
        val archive: Archive = Library.index(Indexes.CONFIG).archive(Configs.SPOT_ANIMS)!!
        val definitions = archive.fileIds().map {
            SpotAnimationProvider.decode(ByteBuffer.wrap(archive.file(it)?.data), SpotAnimationDefinition(it))
        }
        return Serializable(this, definitions, "/spotanimations")
    }

}