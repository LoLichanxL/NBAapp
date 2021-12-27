package com.bobnevpavel.domain.usecases

import com.bobnevpavel.domain.repositories.NbaRepository

class NbaNewsUseCase(val nbaRepository: NbaRepository) {
    suspend operator fun invoke() = nbaRepository.getNews()
}