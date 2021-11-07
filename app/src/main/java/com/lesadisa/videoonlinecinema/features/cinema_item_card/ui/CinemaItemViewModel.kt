package com.lesadisa.videoonlinecinema.features.cinema_item_card.ui

import com.lesadisa.videoonlinecinema.base.BaseViewModel
import com.lesadisa.videoonlinecinema.base.Event
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor

class CinemaItemViewModel(
    private val cinemaInteractor: CinemaInteractor
) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState()

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnPlayButtonCLick -> {

            }
        }
        return null
    }
}