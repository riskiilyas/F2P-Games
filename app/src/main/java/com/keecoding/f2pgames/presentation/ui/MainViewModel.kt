package com.keecoding.f2pgames.presentation.ui

import android.util.Log
import androidx.lifecycle.*
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import java.sql.Time
import java.sql.Timestamp
import java.text.Format
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val clearAllCacheUseCase: ClearAllCacheUseCase,
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getFavoriteGamesUseCase: GetFavoriteGamesUseCase,
    private val getGameDetailUseCase: GetGameDetailUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModel() {

    private val _gamesLiveData = MutableLiveData<Resource<List<GameModel>>>()
    val gamesLiveData : LiveData<Resource<List<GameModel>>> get() = _gamesLiveData

    private val _gameDetailLiveData = MutableLiveData<Resource<GameDetailModel>>()
    val gameDetailLiveData : LiveData<Resource<GameDetailModel>> get() = _gameDetailLiveData

    fun getAllGames() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                getAllGamesUseCase.execute(GetAllGamesUseCase.ALL).collect {
                    _gamesLiveData.postValue(it)
                }
            }
        }
    }

    fun getGameDetail(id: Int) {
        Log.d("ddddd", "getGameDetail: $id")
        viewModelScope.launch {
            getGameDetailUseCase.execute(id)
                .catch {}
                .collect {
                    _gameDetailLiveData.postValue(it)
                }
        }
    }

    fun clearAllCache() {
        viewModelScope.launch {
            clearAllCacheUseCase.execute()
        }
    }

    fun addToFavorite(gameModel: GameModel) {
        viewModelScope.launch {
            addToFavoriteUseCase.execute(gameModel)
        }
    }

    fun clearGameDetail() {
        _gameDetailLiveData.postValue(Resource.Empty())
    }
}