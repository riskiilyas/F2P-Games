package com.keecoding.f2pgames.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keecoding.f2pgames.domain.usecases.*
import com.keecoding.f2pgames.presentation.ui.MainViewModel

class ViewModelFactory(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val clearAllCacheUseCase: ClearAllCacheUseCase,
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
    private val getGameDetailUseCase: GetGameDetailUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            clearAllCacheUseCase,
            getAllGamesUseCase,
            getFavoriteGamesUseCase,
            getGameDetailUseCase,
            addToFavoriteUseCase,
            removeFavoriteUseCase
        ) as T
    }
}