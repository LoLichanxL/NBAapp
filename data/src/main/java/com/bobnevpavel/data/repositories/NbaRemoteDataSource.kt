package com.bobnevpavel.data.repositories

import com.bobnevpavel.domain.common.Result
import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.entities.News

interface NbaRemoteDataSource {
    suspend fun getAllNews(): Result<List<News>>
    suspend fun getAllGames(): Result<List<Game>>
}