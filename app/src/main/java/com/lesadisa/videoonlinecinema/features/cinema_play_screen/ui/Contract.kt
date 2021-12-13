package com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui

import com.lesadisa.videoonlinecinema.base.Event


data class PlayerViewState(val url: String)

sealed class PlayerUiEvent : Event {
    data class OnPlayerStarted(val url: String) : PlayerUiEvent()
}