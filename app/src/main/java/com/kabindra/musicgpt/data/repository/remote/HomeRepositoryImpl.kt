package com.kabindra.musicgpt.data.repository.remote

import com.kabindra.musicgpt.data.model.HomeDTO
import com.kabindra.musicgpt.data.model.HomeDataDTO
import com.kabindra.musicgpt.data.model.MusicDTO
import com.kabindra.musicgpt.data.model.toDomain
import com.kabindra.musicgpt.data.source.remote.ApiDataSource
import com.kabindra.musicgpt.domain.model.Home
import com.kabindra.musicgpt.domain.repository.remote.HomeRepository
import com.kabindra.musicgpt.utils.ktor.Result
import com.kabindra.musicgpt.utils.ktor.ResultError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : HomeRepository {

    override suspend fun getHomeData(): Flow<Result<Home>> =
        flow {
            emit(Result.Loading)
            try {
                val response: List<MusicDTO> = apiDataSource.getHome()

                // Imitate Api hit
                delay(2000)

                val musics = response

                emit(Result.Success(HomeDTO(response = HomeDataDTO(music = musics)).toDomain()))
            } catch (e: Exception) {
                emit(Result.Error(ResultError.parseException(e)))
            }
        }

}