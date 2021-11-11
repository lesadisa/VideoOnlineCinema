package com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.databinding.FragmentPlayerBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class PlayFragment : Fragment() {
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: CinemaDomainModel) = PlayFragment().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding
            .inflate(inflater, container, false)
        return binding.root
    }
}

/* private var mPlayer: SimpleExoPlayer? = null
 private lateinit var playerView: PlayerView

 private val videoURL =
     "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     //get PlayerView by its id
     playerView = findViewById(R.id.playerView)

 }

 private fun initPlayer() {

     // Create a player instance.
     mPlayer = SimpleExoPlayer.Builder(this).build()

     // Bind the player to the view.
     playerView.player = mPlayer

     //setting exoplayer when it is ready.
     mPlayer!!.playWhenReady = true

     // Set the media source to be played.
     mPlayer!!.setMediaSource(buildMediaSource())

     // Prepare the player.
     mPlayer!!.prepare()

 }

 override fun onStart() {
     super.onStart()
     if (Util.SDK_INT >= 24) {
         initPlayer()
     }
 }

 override fun onResume() {
     super.onResume()
     if (Util.SDK_INT < 24 || mPlayer == null) {
         initPlayer()
     }
 }

 override fun onPause() {
     super.onPause()
     if (Util.SDK_INT < 24) {
         releasePlayer()
     }
 }

 override fun onStop() {
     super.onStop()
     if (Util.SDK_INT >= 24) {
         releasePlayer()
     }
 }


 private fun releasePlayer() {
     if (mPlayer == null) {
         return
     }
     //release player when done
     mPlayer!!.release()
     mPlayer = null
 }

 //creating mediaSource
 private fun buildMediaSource(): MediaSource {
     // Create a data source factory.
     val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

     // Create a progressive media source pointing to a stream uri.
     val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
         .createMediaSource(MediaBrowser.MediaItem.fromUri(videoURL))

     return mediaSource
 }
}*/


