package com.keecoding.f2pgames.data.api

import com.keecoding.f2pgames.data.api.response_model.GameItemResponse
import com.keecoding.f2pgames.data.api.response_model.gamedetail.GameDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("games")
    fun getGames(
//        @Query("platform") platform: String? = null,
//        @Query("tag") tag: String? = null,
//        @Query("category") category: String? = null
    ): Call<List<GameItemResponse>>

    @GET("game")
    fun getGameDetail(
        @Query("id") id: Int
    ): Call<GameDetailResponse>
}