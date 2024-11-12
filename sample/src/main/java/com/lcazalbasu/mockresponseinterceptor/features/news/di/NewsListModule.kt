package com.lcazalbasu.mockresponseinterceptor.features.news.di

import com.lcazalbasu.mockresponseinterceptor.features.news.usecases.LoadNewsListUseCase
import com.lcazalbasu.mockresponseinterceptor.features.news.usecases.LoadNewsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsListModule {
    @Binds
    abstract fun bindLoadNewsListUseCase(
        loadNewsListUseCaseImpl: LoadNewsListUseCaseImpl,
    ): LoadNewsListUseCase
}
