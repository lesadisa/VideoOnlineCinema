package com.lesadisa.videoonlinecinema

import android.app.Application
import com.lesadisa.videoonlinecinema.features.cinema_card.di.cinemaItemCardmodule
import com.lesadisa.videoonlinecinema.staroe.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VOCApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VOCApp)
            modules(appModule, cinemaItemCardmodule)
        }
    }
}