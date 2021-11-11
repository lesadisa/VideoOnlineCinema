package com.lesadisa.videoonlinecinema.features.cinema_card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.base.loadImage
import com.lesadisa.videoonlinecinema.databinding.FragmentCinemaCardBinding
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui.PlayFragment


class CinemaCardFragment : Fragment() {
    private var _binding: FragmentCinemaCardBinding? = null
    private val binding get() = _binding!!

    //  private val cardViewModel by viewModel<CinemaCardViewModel>()
    /* private val cardViewModel: CinemaCatalogViewModel by viewModels({requireParentFragment()})
      */
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

            cardMovieTitle.text = currMovie.originalTitle

            // отрабатываем вывод URl

            ibtToPlay.setOnClickListener {

                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, PlayFragment.newInstance(currMovie))
                    .addToBackStack("movies").commit()
            }
        }


    }
    //  что то здесь не так
    //   cardViewModel.singleLiveEvents.observe(viewLifecycleOwner, ::onSingleEvent)

    // обрабатываем пришедший SingleEvent

    private fun onSingleEvent(event: SingleEvent) {

        /* when (event) {
             is SingleEvent.OpenPlayCard -> {

                 parentFragmentManager.beginTransaction()
                     .add(R.id.cardContainer, PlayFragment.newInstance(event.cinema))
                     .addToBackStack("movies")
                     .commit()
             }
         }*/
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}