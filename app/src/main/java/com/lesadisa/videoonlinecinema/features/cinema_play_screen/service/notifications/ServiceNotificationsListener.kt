package com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.PlayerService
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui.MoviePlayerFragment


@SuppressLint("RemoteViewLayout")
class ServiceNotificationsListener(
    private val playerService: PlayerService
) : PlayerNotificationManager.NotificationListener {


    private val remoteView
            by lazy { RemoteViews(playerService.packageName, R.layout.fragment_player) }

    private val manager =
        playerService.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

    override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
        super.onNotificationCancelled(notificationId, dismissedByUser)
        playerService.apply {
            stopForeground(true)
            stopSelf()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onNotificationPosted(
        notificationId: Int,
        notification: Notification,
        ongoing: Boolean
    ) {
        super.onNotificationPosted(notificationId, notification, ongoing)
        val notificationBuilder = NotificationCompat.Builder(
            playerService.applicationContext,
            PlayerService.NOTIFICATION_CHANNEL_ID
        )

        val playerIntent = PendingIntent.getService(
            playerService.applicationContext,
            0,
            Intent(playerService.applicationContext, PlayerService::class.java),
            PendingIntent.FLAG_MUTABLE
        )
        val intent = Intent(playerService, MoviePlayerFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(playerService, 0, intent, 0)

        notificationBuilder.apply {
            setContent(remoteView)
            addAction(R.drawable.ic_play_movie, "VideoPlayer", playerIntent)
            setSmallIcon(R.drawable.ic_play_movie)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setCategory(NotificationCompat.CATEGORY_SERVICE)
            setPublicVersion(notification)
            //  setContentIntent(pendingIntent)

        }





        playerService.apply {
            if (Build.VERSION.SDK_INT > 26) {
                manager.createNotificationChannel(
                    NotificationChannel(
                        PlayerService.NOTIFICATION_CHANNEL_ID,
                        PlayerService.NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT,
                    )
                )

                notificationBuilder.setChannelId(PlayerService.NOTIFICATION_CHANNEL_ID)
                val mNotification = notificationBuilder.build()
                startForeground(PlayerService.NOTIFICATION_ID, mNotification)
                manager.notify(PlayerService.NOTIFICATION_ID, mNotification)
            }
            startForeground(PlayerService.NOTIFICATION_ID, notification)
            manager.notify(PlayerService.NOTIFICATION_ID, notification)
        }
    }
}
