package com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui

import com.lesadisa.videoonlinecinema.base.Event
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

//поросить рассказать
data class ViewState(
    val cinema: List<CinemaDomainModel>,
    val errorMessage: String?,
    val isLoading: Boolean
) {
    val isInErrorState: Boolean = errorMessage != null
}

sealed class UiEvent : Event {
    object FetchMovies : UiEvent()
    data class OnPosterClick(val cinema: CinemaDomainModel) : UiEvent()


}


sealed class DataEvent : Event {
    object OnFetching : DataEvent()
    data class SuccessMoviesRequest(val cinema: List<CinemaDomainModel>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent : Event {
    data class OpenMovieCard(val cinema: CinemaDomainModel) : SingleEvent()
}

// добавил для теста
sealed class PlayEvent : Event {
    data class OpenPlayCard(val cinema: CinemaDomainModel) : PlayEvent()
}
