package com.lesadisa.videoonlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class CinemaOnlineResultsGenresModel(
// непонятно как реализовать поля с несколькими одинаковыми ключами, но разными значениями
    @SerializedName("name")
    val id: String,
    @SerializedName("name")
    val name: String


)
