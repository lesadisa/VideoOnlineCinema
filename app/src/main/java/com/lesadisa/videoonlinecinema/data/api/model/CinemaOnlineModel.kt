package com.lesadisa.videoonlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class CinemaOnlineModel(
    @SerializedName("page")
    val pageOnline: Int,
    @SerializedName("results")
    val resultsOnline: List<CinemaOnlineResultsModel>,
    @SerializedName("total_pages")
    val total_pagesOnline: Long,
    @SerializedName("total_results")
    val total_resultsOnline: Long

)

