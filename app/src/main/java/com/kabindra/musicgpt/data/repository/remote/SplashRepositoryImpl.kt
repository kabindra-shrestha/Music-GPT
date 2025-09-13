package com.kabindra.musicgpt.data.repository.remote

import com.kabindra.musicgpt.data.model.SplashCheckDTO
import com.kabindra.musicgpt.data.model.toDomain
import com.kabindra.musicgpt.domain.model.SplashCheck
import com.kabindra.musicgpt.domain.repository.remote.SplashRepository
import com.kabindra.musicgpt.utils.ktor.Result
import com.kabindra.musicgpt.utils.ktor.ResultError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SplashRepositoryImpl(
) : SplashRepository {

    override suspend fun getCheckSplash(): Flow<Result<SplashCheck>> =
        flow {
            emit(Result.Loading)
            try {
                // Imitate Api hit
                delay(1000)

                emit(Result.Success(SplashCheckDTO(response = true).toDomain()))
            } catch (e: Exception) {
                emit(Result.Error(ResultError.parseException(e)))
            }
        }

}