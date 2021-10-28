package com.lesadisa.videoonlinecinema.staroe.di

import com.lesadisa.videoonlinecinema.Const.HttpConst
import com.lesadisa.videoonlinecinema.data.api.CinemaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATA_BASE = "DATA_BASE"
val appModule = module {
    single<OkHttpClient> {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(HttpConst.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CinemaApi> {
        get<Retrofit>().create(CinemaApi::class.java)
    }

}


