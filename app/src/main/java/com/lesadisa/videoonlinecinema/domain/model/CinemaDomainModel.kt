package com.lesadisa.videoonlinecinema.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CinemaDomainModel(

    val adult: Boolean,//категория взрослый?
    val genres: List<CinemaGenreDomainModel>, //Жанр, может быть несколько
    val id: Int,
    val originalLanguage: String,   // оригинальный язык
    val originalTitle: String, // оригинальное название
    val overview: String,//Краткое описание
    val releaseDate: String,//дата релиза
    val posterPath: String,  //адрес постера
    val popularity: Double?,  //популярность
    val title: String, //Название
    val video: String, // адрес видео
    val voteAverage: Double,    //среднее количество голосов
    val voteCount: Int     //Количество оценивших

) : Parcelable