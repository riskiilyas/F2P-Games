package com.keecoding.f2pgames.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = GameModel.TABLE_NAME)
data class GameModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val developer: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val thumbnail: String,
    val releaseDate: String,
    val shortDescription: String,
    val title: String,
    var isFav: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "GAMEMODEL"
    }
}