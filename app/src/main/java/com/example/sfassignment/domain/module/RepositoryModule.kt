package com.example.sfassignment.domain.module

import com.example.sfassignment.data.api.ApiDataSource
import com.example.sfassignment.data.api.SWApi
import com.example.sfassignment.data.repository.CharacterRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideApiDataSource(swApi: SWApi): ApiDataSource {
        return ApiDataSource(swApi)
    }

    @Provides
    @Singleton
    fun provideRepository(apiDataSource: ApiDataSource): CharacterRepoImpl {
        return CharacterRepoImpl(apiDataSource)
    }
}
