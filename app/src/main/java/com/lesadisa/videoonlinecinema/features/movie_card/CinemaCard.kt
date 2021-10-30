package com.lesadisa.videoonlinecinema.features.movie_card


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.base.loadImage
import com.lesadisa.videoonlinecinema.databinding.FragmentMoviesCardBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class CinemaCard : Fragment() {
    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: CinemaDomainModel) = CinemaCard().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    private var _binding: FragmentMoviesCardBinding? = null
    private val binding get() = _binding!!

    private val currMovie: CinemaDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesCardBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cardPoster.loadImage(currMovie.posterPath)
            cardMovieTitle.text = currMovie.originalTitle
        }
    }
}