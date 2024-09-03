package com.br.devmendesc.playcast.ui.view.podcastDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import kotlinx.coroutines.launch

class PodcastDetailViewModel(
    private val repository: PodcastRepository
) : ViewModel() {

    fun saveEpisode(episode: EpisodeVO) {
        viewModelScope.launch {
            repository.saveEpisode(episode)
        }
    }
}