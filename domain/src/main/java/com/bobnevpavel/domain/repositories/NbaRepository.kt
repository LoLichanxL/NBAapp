package com.bobnevpavel.domain.repositories

import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.entities.News
import com.bobnevpavel.domain.common.Result
interface NbaRepository {
    suspend fun getAllGames() : Result<List<Game>>
    suspend fun getNews() : Result<List<News>>
}