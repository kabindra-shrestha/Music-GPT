package com.kabindra.musicgpt.domain.usecase.remote

import com.kabindra.musicgpt.domain.model.Home
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.domain.repository.remote.HomeRepository
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.Flow

class HomeUseCase(private val repository: HomeRepository) {
    suspend fun executeHomeData(): Flow<Result<Home>> {
        return repository.getHomeData()
    }
    suspend fun executeQueueSong(): Flow<Result<Music>> {
        return repository.queueSong()
    }
    suspend fun executeGenerateSong(): Flow<Result<Music>> {
        return repository.generateSong()
    }
}
