package com.keecoding.f2pgames.domain.usecases

import com.keecoding.f2pgames.data.db.model.GameDetailModel
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(id: Int): Flow<Resource<GameDetailModel>> {
        return repository.getGameDetail(id)
    }
}