package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineResultsGenresModel
import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineResultsModel
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.domain.model.CinemaGenreDomainModel


//Маппинг данных – один из способов для разделения кода приложения на слои.
fun CinemaOnlineResultsModel.toDomain(): CinemaDomainModel {
    val genres: List<CinemaGenreDomainModel> = genresOnline.map { genre -> genre.toDomain() }


    return CinemaDomainModel(
        adult = adultOnlain,
        genres = genres,
        id = idOnline,
        originalLanguage = originalLanguageOnline,
        originalTitle = originalTitleOnline,
        overview = overviewOnline,
        releaseDate = releaseDateOnline,
        posterPath = posterPathOnline,
        popularity = popularityOnline,
        title = titleOnline,
        video = videoOnline,
        voteAverage = voteAverageOnline,
        voteCount = voteCountOnline
    )
}

fun CinemaOnlineResultsGenresModel.toDomain(): CinemaGenreDomainModel =
    CinemaGenreDomainModel(name = name)