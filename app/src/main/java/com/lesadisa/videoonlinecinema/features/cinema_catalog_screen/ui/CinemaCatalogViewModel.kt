package com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui

import com.lesadisa.videoonlinecinema.base.BaseViewModel
import com.lesadisa.videoonlinecinema.base.Event
import com.lesadisa.videoonlinecinema.base.SingleLiveEvent
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor

class CinemaCatalogViewModel(private val cinemaInteractor: CinemaInteractor) :
    BaseViewModel<ViewState>() {
    val singleLiveEvent = SingleLiveEvent<SingleEvent>()


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
            is UiEvent.OnPosterClick -> {
                // singleEvent  это эвент чтобы сделать навигацию для фрагмента
                singleLiveEvent.value = SingleEvent.OpenMovieCard(event.cinema)
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