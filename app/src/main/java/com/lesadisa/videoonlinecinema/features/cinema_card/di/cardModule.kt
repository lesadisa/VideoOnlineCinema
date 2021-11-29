package com.lesadisa.videoonlinecinema.features.cinema_card.di

import com.github.terrakok.cicerone.Router
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.features.cinema_card.ui.CinemaCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cinemaCardmodule = module {
    viewModel<CinemaCardViewModel> {
        CinemaCardViewModel(
            get<CinemaInteractor>(),
            get<Router>()
        )
    }
}
