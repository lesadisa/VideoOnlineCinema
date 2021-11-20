package com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.lesadisa.videoonlinecinema.MainActivity
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.databinding.FragmentPlayerBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class PlayFragment : Fragment() {

    private var exoPlayer: ExoPlayer? = null
    private lateinit var binding: FragmentPlayerBinding
  //  private lateinit var playerNotificationManager: PlayerNotificationManager
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationID = 101


    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: CinemaDomainModel) = PlayFragment().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    private val currMovie: CinemaDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding
            .inflate(inflater, container, false)
        return binding.root
    }


    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            binding.playerView.player = this
            //        setMediaItem(MediaItem.fromUri(currMovie.video))
            setMediaSource(buildMediaSource())
            playWhenReady = true
            initPlayerNotificationManager()
            prepare()
        }
    }

    private fun buildMediaSource(): MediaSource {
        // Create a data source factory.
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

        // Create a progressive media source pointing to a stream uri.
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(currMovie.video))

        return mediaSource
    }

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) {
            initializePlayer()
            createNotification()
        }
    }

    override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer()
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }

    private fun releasePlayer() {
        if (exoPlayer == null) {
            return
        }
        //release player when done 
        exoPlayer!!.release()
        exoPlayer = null
    }

    private fun initPlayerNotificationManager() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification descriptionText"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                name,
                importance
            ).apply { description = descriptionText }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

//        playerNotificationManager.setColor(R.color.design_default_color_primary)
        }
    }

    private fun createNotification() {
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)
        val bitmap: Bitmap? = BitmapFactory.decodeResource(
            requireContext().resources,
            R.drawable.ic_dratwo
        )
        val bitmapLargeIcon: Bitmap =
            BitmapFactory.decodeResource(requireContext().resources, R.drawable.ic_dratw)


        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_placeholder)
            .setContentTitle(currMovie.title)
            .setContentText(currMovie.overview)
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationID, builder.build())


        }

    }


}
