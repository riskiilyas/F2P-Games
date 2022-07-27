package com.keecoding.f2pgames.data.repo.datasource

import com.keecoding.f2pgames.data.api.response_model.GameItemResponse
import com.keecoding.f2pgames.data.api.response_model.gamedetail.GameDetailResponse
import retrofit2.Call

interface RemoteDataSource {
    fun getGames(
        platform: String? = null,
        tag: String? = null,
        category: String? = null
    ): Call<List<GameItemResponse>>

    fun getGameDetail(
        id: Int
    ): Call<GameDetailResponse>
}