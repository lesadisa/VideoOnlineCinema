package com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui


import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.lesadisa.videoonlinecinema.base.BaseViewModel
import com.lesadisa.videoonlinecinema.base.Event

class MoviePlayerViewModel(val exoPlayer: ExoPlayer) : BaseViewModel<PlayerViewState>() {
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun initialViewState() = PlayerViewState(url = "")

    override suspend fun reduce(event: Event, previousState: PlayerViewState): PlayerViewState? {
        when (event) {
            is PlayerUiEvent.OnPlayerStarted -> {
                exoPlayer.apply {
                    setMediaItem(MediaItem.fromUri(event.url))
                }
            }
        }
        return null
    }

    fun initializePlayer() {
        exoPlayer.playWhenReady = playWhenReady
        exoPlayer.seekTo(currentWindow, playbackPosition)
        exoPlayer.prepare()
    }

    fun releasePlayer() {
        exoPlayer.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            release()
        }
    }
}