package com.lesadisa.videoonlinecinema.domain.model

data class CinemaDomainModel(
    //категория взрослый?
    val adult: Boolean,

    //Жанр, может быть несколько
    val genres: CinemaSourseDomainModel,

    val id: Int,

    // оригинальный язык
    val originalLanguage: String,

    // оригинальное название
    val originalTitle: String,

    //Краткое описание
    val overview: String,

    //дата релиза
    val releaseDate: String,

    //адрес постера
    val posterPath: String,

    //популярность
    val popularity: Double?,

    //Название
    val title: String,

    // адрес видео
    val video: String,

    //среднее количество голосов
    val voteAverage: Double,

    //Количество оценивших
    val voteCount: Int


)