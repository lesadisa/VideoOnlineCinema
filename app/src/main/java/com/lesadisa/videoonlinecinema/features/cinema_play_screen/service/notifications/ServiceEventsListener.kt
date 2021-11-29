package com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications

import android.util.Log
import android.widget.Toast
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.PlayerService

class ServiceEventsListener(
    private val playerService: PlayerService
) : Player.Listener {
    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        Log.d("URLxxx", "onPlayWhenReadyChanged")
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        if (reason == Player.STATE_READY && !playWhenReady) {
            playerService.stopForeground(false)
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        Log.d("URLxxx", "onPlayerError")
        super.onPlayerError(error)
        Toast.makeText(playerService, "An unknown error occurred", Toast.LENGTH_LONG).show()
    }
}