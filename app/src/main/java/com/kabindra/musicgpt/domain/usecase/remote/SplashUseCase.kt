package com.kabindra.musicgpt.domain.usecase.remote

import com.kabindra.musicgpt.domain.model.SplashCheck
import com.kabindra.musicgpt.domain.repository.remote.SplashRepository
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.Flow

class SplashUseCase(private val repository: SplashRepository) {
    suspend fun executeCheckSplash(): Flow<Result<SplashCheck>> {
        return repository.getCheckSplash()
    }
}
