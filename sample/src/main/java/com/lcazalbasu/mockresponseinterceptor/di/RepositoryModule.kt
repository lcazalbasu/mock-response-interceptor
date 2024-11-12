package com.lcazalbasu.mockresponseinterceptor.di

import com.lcazalbasu.mockresponseinterceptor.data.repositories.NewsRepository
import com.lcazalbasu.mockresponseinterceptor.data.repositories.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}
