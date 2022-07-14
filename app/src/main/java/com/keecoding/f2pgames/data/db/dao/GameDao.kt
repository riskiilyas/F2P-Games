package com.keecoding.f2pgames.data.db.dao

import androidx.room.*
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.PUT

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameItem(game: GameModel)

    @Query("SELECT * FROM ${GameModel.TABLE_NAME}")
    fun getAllGames(): Flow<List<GameModel>>

    @Query("SELECT * FROM ${GameModel.TABLE_NAME} WHERE isFav = TRUE")
    fun getFavGames(): Flow<List<GameModel>>

    @Query("DELETE FROM ${GameModel.TABLE_NAME}")
    fun clearGameModelssCache()
}