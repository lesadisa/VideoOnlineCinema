package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface CinemaApi {
    @GET()
    @Headers()
    suspend fun fetchList(
    ): CinemaOnlineModel
}