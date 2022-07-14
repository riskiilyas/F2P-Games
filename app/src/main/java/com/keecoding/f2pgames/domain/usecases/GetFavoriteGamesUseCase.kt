package com.keecoding.f2pgames.domain.usecases

import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteGamesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): Flow<Resource<List<GameModel>>> {
        return repository.getFavoriteGames()
    }
}