package com.lcazalbasu.mockresponseinterceptor.di

import com.lcazalbasu.mockresponseinterceptor.data.mappers.NewsMapper
import com.lcazalbasu.mockresponseinterceptor.data.mappers.NewsMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindNewsMapper(newsMapperImpl: NewsMapperImpl): NewsMapper
}
