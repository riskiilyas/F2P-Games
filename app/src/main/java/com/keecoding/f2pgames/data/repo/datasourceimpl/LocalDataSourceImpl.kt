package com.keecoding.f2pgames.data.repo.datasourceimpl

import com.keecoding.f2pgames.data.db.dao.GameDao
import com.keecoding.f2pgames.data.db.dao.GameDetailDao
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.repo.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val gameDao: GameDao,
    private val gameDetailDao: GameDetailDao
): LocalDataSource {
    override fun insertGameItem(game: GameModel) {
        return gameDao.insertGameItem(game)
    }

    override fun getAllGames(
        platform: String?,
        tag: String?,
        category: String?
    ): Flow<List<GameModel>> {
        return gameDao.getAllGames()
    }

    override fun getFavGames(): Flow<List<GameModel>> {
        return gameDao.getFavGames()
    }

    override fun getGameDetail(id: Int): Flow<GameDetailModel> {
        return gameDetailDao.getGameDetail(id)
    }

    override fun insertGameDetail(detailModel: GameDetailModel) {
        return gameDetailDao.insertGameDetail(detailModel)
    }

    override fun clearAllCache() {
        gameDetailDao.clearGameDetailsCache()
        gameDao.clearGameModelssCache()
    }

    override fun removeFavorite(game: GameModel) {
        gameDao.insertGameItem(game.apply { isFav = false })
    }
}