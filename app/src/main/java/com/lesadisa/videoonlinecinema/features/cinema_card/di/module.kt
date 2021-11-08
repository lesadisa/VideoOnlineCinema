package com.lesadisa.videoonlinecinema.features.cinema_card.di

import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.features.cinema_card.ui.CinemaCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cinemaItemCardmodule = module {

    viewModel<CinemaCardViewModel> {
        CinemaCardViewModel(get<CinemaInteractor>())
    }

}