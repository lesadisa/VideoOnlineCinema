package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.Const.HttpConst.BASE_MOVIES_PATH
import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface CinemaApi {
    @GET(BASE_MOVIES_PATH)
    @Headers()
    suspend fun fetchList(
    ): CinemaOnlineModel
}