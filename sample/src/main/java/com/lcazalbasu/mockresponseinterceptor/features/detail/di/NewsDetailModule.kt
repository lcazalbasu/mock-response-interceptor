package com.lcazalbasu.mockresponseinterceptor.features.detail.di

import com.lcazalbasu.mockresponseinterceptor.features.detail.usecases.LoadNewsDetailUseCase
import com.lcazalbasu.mockresponseinterceptor.features.detail.usecases.LoadNewsDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDetailModule {
    @Binds
    abstract fun bindLoadNewsDetailUseCase(
        loadNewsDetailUseCaseImpl: LoadNewsDetailUseCaseImpl,
    ): LoadNewsDetailUseCase
}
