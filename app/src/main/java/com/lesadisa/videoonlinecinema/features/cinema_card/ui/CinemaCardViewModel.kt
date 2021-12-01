package com.lesadisa.videoonlinecinema.features.cinema_card.ui

import com.github.terrakok.cicerone.Router
import com.lesadisa.videoonlinecinema.base.BaseViewModel
import com.lesadisa.videoonlinecinema.base.Event
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.navigation.Screens


class CinemaCardViewModel(
    private val cinemaInteractor: CinemaInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.FetchMovies)
    }

    override fun initialViewState(): ViewState = ViewState(
        cinema = emptyList(),
        errorMessage = null,
        isLoading = false
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.FetchMovies -> {
                processDataEvent(DataEvent.OnFetching)
                cinemaInteractor.fetchCinema().fold(
                    onSuccess = { cinema ->
                        processDataEvent(DataEvent.SuccessMoviesRequest(cinema = cinema))
                    },
                    onError = { exception ->
                        processDataEvent(
                            DataEvent.ErrorMoviesRequest(
                                exception.localizedMessage ?: ""
                            )
                        )
                    }
                )
            }
            //обрабатываем нажатие (получено из adaptera)
            is UiEvent.OnPlayClick -> {
                             router.navigateTo(Screens.moviePlayer(event.cinema.video))
            }

            is DataEvent.OnFetching -> {
                return previousState.copy(isLoading = !previousState.isLoading)
            }

            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(
                    cinema = event.cinema,
                    isLoading = false,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
        }
        return null
    }
}