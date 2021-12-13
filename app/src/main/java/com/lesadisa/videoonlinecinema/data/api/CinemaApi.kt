package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.Const.HttpConst.BASE_MOVIES_PATH
import com.lesadisa.videoonlinecinema.Const.HttpConst.CACHE_CONTROL_HEADER
import com.lesadisa.videoonlinecinema.Const.HttpConst.CACHE_CONTROL_NO_CACHE
import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface CinemaApi {
    @GET(BASE_MOVIES_PATH)
    @Headers("$CACHE_CONTROL_HEADER: $CACHE_CONTROL_NO_CACHE")
    suspend fun fetchList(
    ): CinemaOnlineModel
}