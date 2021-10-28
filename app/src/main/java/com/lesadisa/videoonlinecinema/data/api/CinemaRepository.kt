package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

interface CinemaRepository {
    suspend fun fetchList(): List<CinemaDomainModel>
}