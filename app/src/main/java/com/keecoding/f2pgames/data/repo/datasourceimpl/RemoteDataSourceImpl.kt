package com.keecoding.f2pgames.data.repo.datasourceimpl

import com.keecoding.f2pgames.data.api.APIService
import com.keecoding.f2pgames.data.api.response_model.GameItemResponse
import com.keecoding.f2pgames.data.api.response_model.gamedetail.GameDetailResponse
import com.keecoding.f2pgames.data.repo.datasource.RemoteDataSource
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val gameService: APIService
): RemoteDataSource {
    override fun getGames(
        platform: String?,
        tag: String?,
        category: String?
    ): Call<List<GameItemResponse>> {
        return gameService.getGames()
    }

    override fun getGameDetail(id: Int): GameDetailResponse {
        return gameService.getGameDetail(id)
    }
}