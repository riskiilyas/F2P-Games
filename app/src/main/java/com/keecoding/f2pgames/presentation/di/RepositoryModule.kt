package com.keecoding.f2pgames.presentation.di

import com.keecoding.f2pgames.data.api.APIService
import com.keecoding.f2pgames.data.db.dao.GameDao
import com.keecoding.f2pgames.data.db.dao.GameDetailDao
import com.keecoding.f2pgames.data.repo.RepositoryImpl
import com.keecoding.f2pgames.data.repo.datasource.LocalDataSource
import com.keecoding.f2pgames.data.repo.datasource.RemoteDataSource
import com.keecoding.f2pgames.data.repo.datasourceimpl.LocalDataSourceImpl
import com.keecoding.f2pgames.data.repo.datasourceimpl.RemoteDataSourceImpl
import com.keecoding.f2pgames.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        gameDao: GameDao,
        gameDetailDao: GameDetailDao
    ): LocalDataSource = LocalDataSourceImpl(gameDao, gameDetailDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: APIService
    ): RemoteDataSource = RemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository = RepositoryImpl(localDataSource, remoteDataSource)

}