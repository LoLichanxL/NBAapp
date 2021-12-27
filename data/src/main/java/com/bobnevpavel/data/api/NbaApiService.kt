package com.bobnevpavel.data.api

import com.bobnevpavel.data.entities.AllGamesResponseItem
import com.bobnevpavel.data.entities.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaApiService {
    @GET("scores/json/Games/2022")
    suspend fun getAllMatchesBySeason(@Query("key") apiKey: String): Response<List<AllGamesResponseItem>>

    @GET("scores/json/News")
    suspend fun getNews(@Query("key") apiKey: String): Response<List<NewsResponse>>
}