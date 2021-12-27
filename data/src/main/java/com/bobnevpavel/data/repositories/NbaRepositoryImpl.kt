package com.bobnevpavel.data.repositories

import android.util.Log
import com.bobnevpavel.data.api.NbaApiService
import com.bobnevpavel.data.mappers.NbaApiMapper
import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.common.Result
import com.bobnevpavel.domain.entities.News
import com.bobnevpavel.domain.repositories.NbaRepository
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NbaRepositoryImpl @Inject constructor(val remoteDataSource: NbaRemoteDataSource) : NbaRepository {
    override suspend fun getAllGames(): Result<List<Game>> {
        return remoteDataSource.getAllGames()
    }

    override suspend fun getNews(): Result<List<News>>{
        return remoteDataSource.getAllNews()

    }

}