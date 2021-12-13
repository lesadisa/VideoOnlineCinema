package com.lesadisa.videoonlinecinema

//import com.lesadisa.videoonlinecinema.features.cinema_card.di.cinemaItemCardmodule
import android.app.Application
import com.lesadisa.videoonlinecinema.di.appModule
import com.lesadisa.videoonlinecinema.di.navModule
import com.lesadisa.videoonlinecinema.features.cinema_card.di.cinemaCardmodule
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.di.videoPlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VOCApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VOCApp)
            modules(appModule, navModule, cinemaCardmodule, videoPlayerModule)
        }
    }
}