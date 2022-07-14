package com.keecoding.f2pgames.domain.usecases

import com.keecoding.f2pgames.domain.Repository
import javax.inject.Inject

class ClearAllCacheUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(){
        repository.clearAllCache()
    }
}