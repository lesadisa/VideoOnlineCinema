package com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.databinding.FragmentCinemaCatalogBinding
import com.lesadisa.videoonlinecinema.features.cinema_card.ui.CinemaCardFragment
import com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui.adapter.CinemaCatalogAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CinemaCatalogFragment : Fragment() {
    companion object {
        fun newInstance() = CinemaCatalogFragment()
    }


    private var _binding: FragmentCinemaCatalogBinding? = null
    private val binding get() = _binding!!

    // private lateinit var binding: FragmentCinemaCatalogBinding
    private val moviesViewModel by viewModel<CinemaCatalogViewModel>()

    //получаем сообщение от Adaptera о нажатии
    private val moviesCatalogAdapter: CinemaCatalogAdapter by lazy {
        CinemaCatalogAdapter(movies = emptyList()) { movie ->
            moviesViewModel.processUiEvent(UiEvent.OnPosterClick(movie)) //переходим на OnPosterClick в Contract
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCinemaCatalogBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            adapter = moviesCatalogAdapter
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
        moviesCatalogAdapter.updateList(viewState.cinema)
    }

    // обрабатываем пришедший SingleEvent
    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieCard -> {

                parentFragmentManager.beginTransaction()
                    .add(R.id.moviesContainer, CinemaCardFragment.newInstance(event.cinema))
                    .addToBackStack("movies")
                    .commit()
            }
        }
    }


}
