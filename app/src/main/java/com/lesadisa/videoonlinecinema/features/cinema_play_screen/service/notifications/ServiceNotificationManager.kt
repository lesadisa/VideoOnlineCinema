package com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.PlayerService

class ServiceNotificationManager(
    private val context: Context,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val mediaTitle: String
) {
    private val notificationManager: PlayerNotificationManager

    init {
        notificationManager = PlayerNotificationManager.Builder(
            context,
            PlayerService.NOTIFICATION_ID,
            PlayerService.NOTIFICATION_CHANNEL_ID,
        ).apply {
            setChannelNameResourceId(R.string.notification_channel_name)
            setChannelDescriptionResourceId(R.string.notification_channel_description)
            setSmallIconResourceId(R.drawable.ic_dratwo)
            setMediaDescriptionAdapter(DescriptionsAdapter())
            setNotificationListener(notificationListener)



        }.build()


    }


    fun showNotification(player: Player) {
        notificationManager.setPlayer(player)
    }

    private inner class DescriptionsAdapter : PlayerNotificationManager.MediaDescriptionAdapter {
        override fun getCurrentContentTitle(player: Player): CharSequence {
            return context.getString(R.string.notification_channel_name)
        }

        @RequiresApi(Build.VERSION_CODES.S)
        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return PendingIntent.getService(
                context,
                0,
                Intent(context, PlayerService::class.java).apply {
                    putExtra(PlayerService.PLAY_PAUSE_ACTION, 0)
                },
                PendingIntent.FLAG_MUTABLE
            )
        }

        override fun getCurrentContentText(player: Player): CharSequence {
            // return context.getString(R.string.notification_channel_description)
            return mediaTitle
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            loadBitmap(callback)
            return null
        }
    }

    private fun loadBitmap(callback: PlayerNotificationManager.BitmapCallback?) {
        Glide.with(context)
            .asBitmap()
            .load(R.drawable.ic_dratwo)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    callback?.onBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}