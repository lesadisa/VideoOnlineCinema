package com.lesadisa.videoonlinecinema.features.play_card

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.databinding.FragmentMoviePlayerBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class PlayCard : Fragment() {
    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: CinemaDomainModel) = PlayCard().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    private var _binding: FragmentMoviePlayerBinding? = null
    private val binding get() = _binding!!

    private val currMovie: CinemaDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviePlayerBinding
            .inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            videoPlayer.setVideoURI(Uri.parse(currMovie.video))

        }
    }


}