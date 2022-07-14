package com.keecoding.f2pgames.domain.usecases

import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.data.util.Resource
import com.keecoding.f2pgames.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGamesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(
        platform: String,
        tag: String? = null,
        category: String? = null
    ): Flow<Resource<List<GameModel>>> {
        return repository.getAllGames(platform, tag, category)
    }

    companion object {
        const val ALL = "ALL"
        const val PC = "PC"
        const val  WEB = "WEB"
    }
}