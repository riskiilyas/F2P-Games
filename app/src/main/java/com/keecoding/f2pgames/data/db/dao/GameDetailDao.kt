package com.keecoding.f2pgames.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameDetail(gameDetailModel: GameDetailModel)

    @Query("SELECT * FROM ${GameDetailModel.TABLE_NAME} WHERE id = :id")
    fun getGameDetail(id: Int): Flow<GameDetailModel>

    @Query("DELETE FROM ${GameDetailModel.TABLE_NAME}")
    fun clearGameDetailsCache()
}