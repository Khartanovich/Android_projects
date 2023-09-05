package com.example.jetpackccompose.domain

import com.example.jetpackccompose.entity.EpisodeEntity
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(private val repository: MyRepository) {
    suspend fun getEpisode(listId: List<Int>): List<EpisodeEntity>{
        return repository.getEpisode(listId)
    }
}