package com.keecoding.f2pgames.data.repo

import com.keecoding.f2pgames.data.api.response_model.GameItemResponse
import com.keecoding.f2pgames.data.api.response_model.gamedetail.GameDetailResponse
import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.repo.datasource.LocalDataSource
import com.keecoding.f2pgames.data.repo.datasource.RemoteDataSource
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.data.util.networkBoundResource
import com.keecoding.f2pgames.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun getAllGames(
        platform: String,
        tag: String?,
        category: String?
    ): Flow<Resource<List<GameModel>>> {
    var isSuccess = true
        return networkBoundResource(
            query = {
                localDataSource.getAllGames()
            },
            fetch = {
                remoteDataSource.getGames(platform, tag, category)
            },
            saveFetchResult = {
                it.enqueue(object : Callback<List<GameItemResponse>> {
                    override fun onResponse(
                        call: Call<List<GameItemResponse>>,
                        response: Response<List<GameItemResponse>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            response.body()?.forEach { item ->
                                localDataSource.insertGameItem(item.toGameModel())
                            }
                            isSuccess = true
                        } else {
                            isSuccess = false
                        }
                    }

                    override fun onFailure(call: Call<List<GameItemResponse>>, t: Throwable) {
                        isSuccess = false
                    }
                })
            },
            isFetchSuccess = { isSuccess }
        )
    }

    override suspend fun getGameDetail(id: Int): Flow<Resource<GameDetailModel>> {
        return networkBoundResource(
            query = {
                localDataSource.getGameDetail(id)
            },
            fetch = {
                remoteDataSource.getGameDetail(id)
            },
            saveFetchResult = {
                it.enqueue(object : Callback<GameDetailResponse> {
                    override fun onResponse(
                        call: Call<GameDetailResponse>,
                        response: Response<GameDetailResponse>
                    ) {
                        if(response.isSuccessful) {
                            response.body()?.let { detail ->
                                localDataSource.insertGameDetail(detail.toGameDetailModel())
                            }
                        }
                    }

                    override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                    }
                })
            }
        )
    }

    override suspend fun getFavoriteGames(): Flow<Resource<List<GameModel>>> {
        return localDataSource.getFavGames().map {
            Resource.Success(it)
        }
    }

    override suspend fun clearAllCache() {
        localDataSource.clearAllCache()
    }

    override suspend fun addToFavorite(gameModel: GameModel) {
        localDataSource.insertGameItem(gameModel.apply { isFav = true })
    }

    override suspend fun removeFavorite(gameModel: GameModel) {
        localDataSource.insertGameItem(gameModel.apply { isFav = false })
    }
}