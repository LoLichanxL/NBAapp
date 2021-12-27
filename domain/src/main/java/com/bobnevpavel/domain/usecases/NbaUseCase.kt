package com.bobnevpavel.domain.usecases

import com.bobnevpavel.domain.repositories.NbaRepository
class NbaUseCase(val nbaRepository: NbaRepository) {
    suspend operator fun invoke() = nbaRepository.getAllGames()

}