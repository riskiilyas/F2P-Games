package com.keecoding.f2pgames.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.keecoding.f2pgames.data.db.dao.GameDao
import com.keecoding.f2pgames.data.db.dao.GameDetailDao
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.db.type_converter.ScreenshotsConverter

@Database(entities = [GameModel::class, GameDetailModel::class], version = 1, exportSchema = false)
@TypeConverters(ScreenshotsConverter::class)
abstract class AppDB: RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun gameDetailDao(): GameDetailDao
}