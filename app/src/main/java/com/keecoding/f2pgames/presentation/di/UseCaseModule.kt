package com.keecoding.f2pgames.presentation.di

import com.keecoding.f2pgames.domain.Repository
import com.keecoding.f2pgames.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllGamesUseCase(repository: Repository) = GetAllGamesUseCase(repository)

    @Provides
    @Singleton
    fun provideClearAllCacheUseCase(repository: Repository) = ClearAllCacheUseCase(repository)

    @Provides
    @Singleton
    fun provideAddToFavoriteUseCase(repository: Repository) = AddToFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetGameDetailUseCase(repository: Repository) = GetGameDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFavoriteUseCase(repository: Repository) = RemoveFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoriteGamesUseCase(repository: Repository) = GetFavoriteGamesUseCase(repository)
}