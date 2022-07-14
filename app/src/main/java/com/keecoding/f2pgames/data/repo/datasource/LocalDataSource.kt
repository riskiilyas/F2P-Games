package com.keecoding.f2pgames.data.repo.datasource

import androidx.room.Query
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun insertGameItem(game: GameModel)
    fun getAllGames(
        platform: String? = null,
        tag: String? = null,
        category: String? = null
    ): Flow<List<GameModel>>
    fun getFavGames(): Flow<List<GameModel>>
    fun getGameDetail(id: Int): Flow<GameDetailModel>
    fun insertGameDetail(detailModel: GameDetailModel)
    fun clearAllCache()
    fun removeFavorite(game: GameModel)
}