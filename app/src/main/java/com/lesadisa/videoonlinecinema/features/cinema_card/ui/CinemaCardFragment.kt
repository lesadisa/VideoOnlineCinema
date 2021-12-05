package com.lesadisa.videoonlinecinema.features.cinema_card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.base.loadImage
import com.lesadisa.videoonlinecinema.databinding.FragmentCinemaCardBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui.MoviePlayerFragment


class CinemaCardFragment : Fragment() {
    private var _binding: FragmentCinemaCardBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: CinemaDomainModel) = CinemaCardFragment().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }


    private val currMovie: CinemaDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!

    }

    //Вызывается для создания структуры внутри фрагмента
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCinemaCardBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    // вносим данные во View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cardPoster.loadImage(currMovie.posterPath)
            cardMovieoverview.text = currMovie.overview
            cardMovieTitle.text = currMovie.originalTitle
            // отрабатываем вывод URl
            val animationZoomIn = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_anim)
            val animationRotateIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in)

            cardPoster.startAnimation(animationZoomIn)
            cardMovieoverview.startAnimation(animationZoomIn)
            cardMovieTitle.startAnimation(animationRotateIn)




            ibtToPlay.setOnClickListener {

                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviePlayerFragment.newInstance(currMovie.video))
                    .addToBackStack("movies").commit()
            }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        //binding = null
    }

}