package com.kabindra.musicgpt.domain.repository.remote

import com.kabindra.musicgpt.domain.model.SplashCheck
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    suspend fun getCheckSplash(): Flow<Result<SplashCheck>>
}