package com.bobnevpavel.nbanews.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bobnevpavel.data.repositories.NbaRepositoryImpl
import com.bobnevpavel.domain.common.Result
import com.bobnevpavel.domain.entities.*
import com.bobnevpavel.nbanews.presentation.rv.fingerprints.GameFingerPrint
import com.bobnevpavel.nbanews.presentation.rv.fingerprints.LeagueLabelFingerPrint
import com.bobnevpavel.nbanews.presentation.rv.fingerprints.NewsFingerPrint
import com.bobnevpavel.nbanews.presentation.utils.GameUtil
import javax.inject.Inject
import kotlinx.coroutines.*


class MainFragmentViewModel constructor(val nbaRepositoryImpl: NbaRepositoryImpl): ViewModel() {

    private val _data:MutableLiveData<List<Item>> = MutableLiveData()
    val data:LiveData<List<Item>> = _data
    val fingerPrints = listOf(NewsFingerPrint(), LeagueLabelFingerPrint(), GameFingerPrint())

    suspend fun fetchAllData() = withContext(Dispatchers.IO){
        val newsResult = async{nbaRepositoryImpl.remoteDataSource.getAllNews()}.await()
        val gamesResult = async{nbaRepositoryImpl.remoteDataSource.getAllGames()}.await()
        when(newsResult){
            is Result.Success -> {
                when(gamesResult){
                    is Result.Success -> {
                        Log.d("Games", gamesResult.data.toString())
                        _data.postValue(merge(listOf(newsResult.data[0]), listOf(LeagueLabel("NBA", "United States of America")), GameUtil.filterScheduledGames(gamesResult.data).subList(0, 20))) // Return first news and all games
                    }
                    is  Result.Error -> {
                        throw (gamesResult.exception)
                    }
                }
            }
            is Result.Error -> {
                throw(newsResult.exception)
            }
        }
    }

    private fun <T> merge(first: List<T>, second: List<T>, third:List<T>): List<T> {
        return first + second + third
    }


    class Factory @Inject constructor(val nbaRepositoryImpl: NbaRepositoryImpl):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainFragmentViewModel(nbaRepositoryImpl) as T
        }
    }


}