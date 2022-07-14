package com.keecoding.f2pgames.domain.usecases

import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.domain.Repository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(gameModel: GameModel) {
        repository.removeFavorite(gameModel)
    }
}