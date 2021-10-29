package com.lesadisa.videoonlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class CinemaOnlineResultsModel(

    @SerializedName("adult") val adultOnlain: Boolean, //категория взрослый?
    @SerializedName("genres") val genresOnline: List<CinemaOnlineResultsGenresModel>,//Жанр, может быть несколько
    @SerializedName("id") val idOnline: Int,
    @SerializedName("original_language") val originalLanguageOnline: String,// оригинальный язык
    @SerializedName("original_title") val originalTitleOnline: String,// оригинальное название
    @SerializedName("overview") val overviewOnline: String, //Краткое описание
    @SerializedName("release_date") val releaseDateOnline: String,    //дата релиза
    @SerializedName("poster_path") val posterPathOnline: String,//адрес постера
    @SerializedName("popularity") val popularityOnline: Double?,//популярность
    @SerializedName("title") val titleOnline: String,//Название
    @SerializedName("video") val videoOnline: String,  // адрес видео
    @SerializedName("vote_average") val voteAverageOnline: Double,  //среднее количество голосов
    @SerializedName("vote_count") val voteCountOnline: Int //Количество оценивших


)