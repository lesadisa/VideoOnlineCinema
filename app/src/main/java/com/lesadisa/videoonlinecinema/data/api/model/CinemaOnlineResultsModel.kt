package com.lesadisa.videoonlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class CinemaOnlineResultsModel(
    //категория взрослый?
    @SerializedName("adult")
    val adultOnlain: Boolean,

    //Жанр, может быть несколько
    @SerializedName("genres")
    val genresOnline: CinemaOnlineResultsGenresModel,

    @SerializedName("id")
    val idOnline: Int,

    // оригинальный язык
    @SerializedName("original_language")
    val originalLanguageOnline: String,

    // оригинальное название
    @SerializedName("original_title")
    val originalTitleOnline: String,

    //Краткое описание
    @SerializedName("overview")
    val overviewOnline: String,

    //дата релиза
    @SerializedName("release_date")
    val releaseDateOnline: String,

    //адрес постера
    @SerializedName("poster_path")
    val posterPathOnline: String,

    //популярность
    @SerializedName("popularity")
    val popularityOnline: Double?,

    //Название
    @SerializedName("title")
    val titleOnline: String,

    // адрес видео
    @SerializedName("video")
    val videoOnline: String,

    //среднее количество голосов
    @SerializedName("vote_average")
    val voteAverageOnline: Double,

    //Количество оценивших
    @SerializedName("vote_count")
    val voteCountOnline: Int


)