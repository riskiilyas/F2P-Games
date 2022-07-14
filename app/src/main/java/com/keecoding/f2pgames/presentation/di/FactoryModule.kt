package com.keecoding.f2pgames.presentation.di

import com.keecoding.f2pgames.domain.usecases.*
import com.keecoding.f2pgames.presentation.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        addToFavoriteUseCase: AddToFavoriteUseCase,
        clearAllCacheUseCase: ClearAllCacheUseCase,
        getAllGamesUseCase: GetAllGamesUseCase,
        getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
        getGameDetailUseCase: GetGameDetailUseCase,
        removeFavoriteUseCase: RemoveFavoriteUseCase
    ) = ViewModelFactory(
        addToFavoriteUseCase,
        clearAllCacheUseCase,
        getAllGamesUseCase,
        getFavoriteGamesUseCase,
        getGameDetailUseCase,
        removeFavoriteUseCase
    )
}