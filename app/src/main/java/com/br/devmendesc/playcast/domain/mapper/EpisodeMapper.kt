package com.br.devmendesc.playcast.domain.mapper

import com.br.devmendesc.playcast.domain.models.entities.EpisodeEntity
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO

class EpisodeMapper {

    fun toVO(entity: EpisodeEntity): EpisodeVO {
        return EpisodeVO(
            title = entity.title,
            duration = entity.duration,
            image = entity.image,
            category = entity.category,
            explicit = entity.explicit,
            urlEpisode = entity.urlEpisode
        )
    }

    fun toEntity(vo: EpisodeVO): EpisodeEntity {
        return EpisodeEntity(
            title = vo.title,
            duration = vo.duration,
            image = vo.image,
            category = vo.category,
            explicit = vo.explicit,
            urlEpisode = vo.urlEpisode
        )
    }
}
