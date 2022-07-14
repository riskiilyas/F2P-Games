package com.keecoding.f2pgames.data.api.response_model.gamedetail


import com.google.gson.annotations.SerializedName
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import java.sql.Timestamp

data class GameDetailResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("developer")
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("screenshots")
    val screenshots: List<Screenshot>,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
) {
    fun toGameDetailModel(): GameDetailModel {
        val arrScreenshot = arrayListOf<String>()
        screenshots.forEach {
            arrScreenshot.add(it.image)
        }
        return GameDetailModel(
            id = id,
            title = title,
            status = status,
            shortDescription = shortDescription,
            description = description,
            gameUrl = gameUrl,
            genre = genre,
            platform = platform,
            thumbnail = thumbnail,
            screenshots = arrScreenshot,
            publisher = publisher,
            developer = developer,
            releaseDate = releaseDate,
            os = minimumSystemRequirements.os,
            processor = minimumSystemRequirements.processor,
            memory = minimumSystemRequirements.memory,
            graphics = minimumSystemRequirements.graphics,
            storage = minimumSystemRequirements.storage,
            lastUpdated = System.currentTimeMillis()
        )
    }
}