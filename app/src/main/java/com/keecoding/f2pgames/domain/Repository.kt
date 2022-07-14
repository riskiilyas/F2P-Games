package com.keecoding.f2pgames.domain

import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllGames( platform: String,
                     tag: String? = null,
                     category: String? = null): Flow<Resource<List<GameModel>>>
    suspend fun getGameDetail(id: Int): Flow<Resource<GameDetailModel>>
    suspend fun getFavoriteGames(): Flow<Resource<List<GameModel>>>
    suspend fun clearAllCache()
    suspend fun addToFavorite(gameModel: GameModel)
    suspend fun removeFavorite(gameModel: GameModel)
}