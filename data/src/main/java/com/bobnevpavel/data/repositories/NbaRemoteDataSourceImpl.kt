package com.bobnevpavel.data.repositories

import android.util.Log
import com.bobnevpavel.data.api.NbaApiService
import com.bobnevpavel.data.mappers.NbaApiMapper
import com.bobnevpavel.domain.common.Result
import com.bobnevpavel.domain.entities.Game
import com.bobnevpavel.domain.entities.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class NbaRemoteDataSourceImpl @Inject constructor(
    val service: NbaApiService,
    val mapper: NbaApiMapper,
    @param:Named("nbaApiKey")val apiKey:String
) : NbaRemoteDataSource {
    override suspend fun getAllNews(): Result<List<News>> = withContext(Dispatchers.IO) {
        try {
            val result = service.getNews(apiKey)
            if (result.isSuccessful) {
                return@withContext Result.Success(mapper.mapNewsResponseToNews(result.body()!!))
            } else {
                return@withContext Result.Error(Exception(result.message()))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getAllGames(): Result<List<Game>>  = withContext(Dispatchers.IO){
        try {
            val result = service.getAllMatchesBySeason(apiKey)
            Log.d("Result", result.isSuccessful.toString())
            if (result.isSuccessful){
                return@withContext Result.Success(mapper.mapGamesResponseToGames(result.body()!!))
            }else{
                return@withContext Result.Error(Exception(result.message()))
            }
        }catch (e:Exception){
            return@withContext Result.Error(e)
        }
    }
}