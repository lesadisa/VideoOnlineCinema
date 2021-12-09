package com.lesadisa.videoonlinecinema.features.cinema_play_screen.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications.ServiceEventsListener
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications.ServiceNotificationManager
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications.ServiceNotificationsListener
import org.koin.android.ext.android.inject

class PlayerService : Service() {
    //Как правило объекты-компаньоны используются для объявления переменных и функций,
    // к которым требуется обращаться без создания экземпляра класса.
    // Либо для объявления констант.
    companion object {
        const val VIDEO_FILE = "video"
        const val NOTIFICATION_CHANNEL_NAME = "PlayerService"
        const val NOTIFICATION_CHANNEL_ID = "video"
        const val NOTIFICATION_ID = 1
        const val PLAY_PAUSE_ACTION = "PlayPauseAction"
    }

    private val exoPlayer by inject<ExoPlayer>()
    private var isReady = true
    private var currentMedia: MediaItem? = null
    private var playbackPosition = 0L

    private val notificationManager: ServiceNotificationManager by lazy {
        ServiceNotificationManager(
            this,
            ServiceNotificationsListener(this)
        )
    }
    private val eventsListener: ServiceEventsListener by lazy {
        ServiceEventsListener(this)
    }

    override fun onBind(intent: Intent?): IBinder {

        val media: CinemaDomainModel? = intent?.getParcelableExtra<CinemaDomainModel>(VIDEO_FILE)
        if (media != null) {
            preparePlayer(media.video)
        }
        return PlayerServiceBinder()
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager.showNotification(exoPlayer)


    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer()
    }

    private fun preparePlayer(url: String) {
        exoPlayer.apply {
            playWhenReady = isReady
            seekTo(0, playbackPosition)
            setMediaItem(MediaItem.fromUri(url))
            addListener(eventsListener)
            prepare()


        }
    }

    private fun stopPlayer() {
        exoPlayer.run {
            isReady = playWhenReady
            playbackPosition = 0L
            currentMedia = currentMediaItem
            removeListener(eventsListener)
            release()
        }
    }

    inner class PlayerServiceBinder : Binder() {
        fun getPlayer() = exoPlayer
    }
}