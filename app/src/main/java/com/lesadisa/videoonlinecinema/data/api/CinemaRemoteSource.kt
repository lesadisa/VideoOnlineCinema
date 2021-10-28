package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineModel

class CinemaRemoteSource(private val api: CinemaApi) {
    suspend fun fetchList(): CinemaOnlineModel = api.fetchList()
}
