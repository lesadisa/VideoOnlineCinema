package com.lesadisa.videoonlinecinema.staroe.di

import com.lesadisa.videoonlinecinema.Const.HttpConst
import com.lesadisa.videoonlinecinema.data.api.CinemaApi
import com.lesadisa.videoonlinecinema.data.api.CinemaRemoteSource
import com.lesadisa.videoonlinecinema.data.api.CinemaRepository
import com.lesadisa.videoonlinecinema.data.api.CinemaRepositoryImpl
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.ui.CinemaViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATA_BASE = "DATA_BASE"
val appModule = module {

    /* single<OkHttpClient> {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .build()
    }*/

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(HttpConst.BASE_MOVIES_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CinemaApi> {
        get<Retrofit>().create(CinemaApi::class.java)
    }

    single<CinemaRemoteSource> {
        CinemaRemoteSource(get<CinemaApi>())
    }
    single<CinemaRepository> {
        CinemaRepositoryImpl(get<CinemaRemoteSource>())
    }

    single<CinemaInteractor> {
        CinemaInteractor(get<CinemaRepository>())
    }

    single<CinemaViewModel> {
        CinemaViewModel(get<CinemaInteractor>())
    }
}

