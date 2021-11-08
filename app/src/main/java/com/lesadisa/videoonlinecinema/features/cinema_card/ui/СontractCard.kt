package com.lesadisa.videoonlinecinema.features.cinema_card.ui

import com.lesadisa.videoonlinecinema.base.Event
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

data class ViewState(
    val cinema: List<CinemaDomainModel>,
    val errorMessage: String?,
    val isLoading: Boolean
) {
    val isInErrorState: Boolean = errorMessage != null
}

sealed class UiEvent : Event {
    object FetchMovies : UiEvent()
    data class OnPlayClick(val cinema: CinemaDomainModel) : UiEvent()


}

sealed class DataEvent : Event {
    object OnFetching : DataEvent()
    data class SuccessMoviesRequest(val cinema: List<CinemaDomainModel>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent : Event {
    data class OpenPlayCard(val cinema: CinemaDomainModel) : SingleEvent()
}