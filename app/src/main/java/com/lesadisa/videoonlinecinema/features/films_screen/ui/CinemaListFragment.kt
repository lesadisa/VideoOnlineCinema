package com.lesadisa.videoonlinecinema.features.films_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.databinding.FragmentMoviesListBinding
import com.lesadisa.videoonlinecinema.features.films_screen.ui.adapter.CinemaAdapter
import com.lesadisa.videoonlinecinema.features.movie_card.CinemaCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class CinemaListFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by viewModel<CinemaViewModel>()
    private val moviesAdapter: CinemaAdapter by lazy {
        CinemaAdapter(movies = emptyList()) { movie ->
            moviesViewModel.processUiEvent(UiEvent.OnPosterClick(movie))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = moviesAdapter
        }

        moviesViewModel.viewState.observe(viewLifecycleOwner, ::render)
        moviesViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        binding.pbMovies.isGone = !viewState.isLoading
        moviesAdapter.updateList(viewState.cinema)
    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieCard -> {
                parentFragmentManager.beginTransaction()
                    .add(R.id.moviesContainer, CinemaCard.newInstance(event.cinema))
                    .addToBackStack("movies")
                    .commit()
            }
        }
    }
}
