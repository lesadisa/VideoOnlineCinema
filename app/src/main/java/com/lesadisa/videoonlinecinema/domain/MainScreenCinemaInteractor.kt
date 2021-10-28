package com.lesadisa.videoonlinecinema.domain

import com.lesadisa.videoonlinecinema.base.attempt
import com.lesadisa.videoonlinecinema.data.api.CinemaRepository

class MainScreenCinemaInteractor(val repository: CinemaRepository) {
    suspend fun fetchList() = attempt { repository.fetchList() }
}