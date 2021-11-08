package com.lesadisa.videoonlinecinema.domain

import com.lesadisa.videoonlinecinema.base.attempt
import com.lesadisa.videoonlinecinema.data.api.CinemaRepository

//
class CinemaInteractor(val repository: CinemaRepository) {
    suspend fun fetchCinema() = attempt { repository.fetchList() }
}