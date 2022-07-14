package com.keecoding.f2pgames.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keecoding.f2pgames.data.api.response_model.gamedetail.Screenshot
import java.sql.Timestamp

@Entity(tableName = GameDetailModel.TABLE_NAME)
data class GameDetailModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val status: String,
    val shortDescription: String,
    val description: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val thumbnail: String,
    val screenshots: ArrayList<String>,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String,
    var lastUpdated: Long
) {
    companion object {
        const val TABLE_NAME = "GAMEDETAILMODEL"
    }
}