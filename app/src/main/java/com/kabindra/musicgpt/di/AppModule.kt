package com.kabindra.musicgpt.di

import androidx.compose.material3.SnackbarHostState
import com.kabindra.musicgpt.data.repository.remote.HomeRepositoryImpl
import com.kabindra.musicgpt.data.repository.remote.SplashRepositoryImpl
import com.kabindra.musicgpt.data.source.remote.ApiDataSource
import com.kabindra.musicgpt.domain.repository.remote.HomeRepository
import com.kabindra.musicgpt.domain.repository.remote.SplashRepository
import com.kabindra.musicgpt.domain.usecase.remote.HomeUseCase
import com.kabindra.musicgpt.domain.usecase.remote.SplashUseCase
import com.kabindra.musicgpt.presentation.viewmodel.remote.home.HomeViewModel
import com.kabindra.musicgpt.presentation.viewmodel.remote.splash.SplashViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val provideAppModule = module {
    single { SnackbarHostState() }
}

val provideHttpClientModule = module {
}

val provideApiServiceModule = module {
}

val provideDataSourceModule = module {
    singleOf(::ApiDataSource)
}

val provideRepositoryModule = module {
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()
    singleOf(::SplashRepositoryImpl).bind<SplashRepository>()
}

val provideUseCaseModule = module {
    singleOf(::HomeUseCase)
    singleOf(::SplashUseCase)
}

val provideViewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SplashViewModel)
}