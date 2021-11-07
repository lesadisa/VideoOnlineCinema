package com.lesadisa.videoonlinecinema.features.cinema_item_card.di

import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.features.cinema_item_card.ui.CinemaItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cinemaItemCardmodule = module {

    viewModel<CinemaItemViewModel> {
        CinemaItemViewModel(get<CinemaInteractor>())
    }

}