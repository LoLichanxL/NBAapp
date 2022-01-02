package com.bobnevpavel.nbanews.di

import com.bobnevpavel.data.api.NbaApiService
import com.bobnevpavel.data.mappers.NbaApiMapper
import com.bobnevpavel.data.repositories.NbaRemoteDataSourceImpl
import com.bobnevpavel.data.repositories.NbaRepositoryImpl
import com.bobnevpavel.nbanews.presentation.fragments.MainScreenFragment
import com.bobnevpavel.nbanews.view.MainFragmentViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: MainScreenFragment)
}

@Module
class NetworkModule{

    @Provides
    fun provideNbaService():NbaApiService{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.sportsdata.io/v3/nba/").build().create(NbaApiService::class.java)
    }

    @Provides
    @Named("nbaApiKey")
    fun provideNbaApiKey():String = "cb43c7f2161746d68e127bda74a2b25f"

    @Provides
    fun provideApiMapper():NbaApiMapper{
        return NbaApiMapper()
    }

    @Provides
    fun provideNbaRepositoryImpl(remoteDataSourceImpl: NbaRemoteDataSourceImpl): NbaRepositoryImpl {
        return NbaRepositoryImpl(remoteDataSourceImpl)
    }

    @Provides
    fun provideNbaRemoteDataSourceImpl(service: NbaApiService, mapper: NbaApiMapper, @Named("nbaApiKey") apiKey: String):NbaRemoteDataSourceImpl{
        return NbaRemoteDataSourceImpl(service, mapper, apiKey)
    }
}

@Module
class ViewModelModule{
    @Provides
    fun provideViewModelFactory(nbaRepositoryImpl: NbaRepositoryImpl):MainFragmentViewModel.Factory{
        return MainFragmentViewModel.Factory(nbaRepositoryImpl)
    }
}