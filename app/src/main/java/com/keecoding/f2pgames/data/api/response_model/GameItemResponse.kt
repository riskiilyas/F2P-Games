package com.keecoding.f2pgames.data.api.response_model


import com.google.gson.annotations.SerializedName
import com.keecoding.f2pgames.data.db.model.GameModel

data class GameItemResponse(
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
    @SerializedName("platform")
    val platform: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
) {
    fun toGameModel(): GameModel {
        return GameModel(
            id = id,
            developer = developer,
            gameUrl = gameUrl,
            genre = genre,
            platform = platform,
            publisher = publisher,
            thumbnail = thumbnail,
            releaseDate = releaseDate,
            shortDescription = shortDescription,
            title = title,
        )
    }
}