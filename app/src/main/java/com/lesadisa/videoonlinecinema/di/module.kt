package com.lesadisa.videoonlinecinema.di

import org.koin.dsl.module
import retrofit2.Retrofit

const val DATA_BASE = "DATA_BASE"
val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(DATA_BASE)
    }
}