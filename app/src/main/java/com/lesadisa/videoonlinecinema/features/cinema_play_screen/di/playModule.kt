package com.lesadisa.videoonlinecinema.features.cinema_play_screen.di

import com.google.android.exoplayer2.ExoPlayer
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val videoPlayerModule = module {
    factory<ExoPlayer> {
        ExoPlayer.Builder(androidApplication()).build()
    }
}