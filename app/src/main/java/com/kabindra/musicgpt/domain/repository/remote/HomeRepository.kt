package com.kabindra.musicgpt.domain.repository.remote

import com.kabindra.musicgpt.domain.model.Home
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHomeData(): Flow<Result<Home>>
    suspend fun queueSong(): Flow<Result<Music>>
    suspend fun generateSong(): Flow<Result<Music>>
}