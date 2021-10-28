package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineResultsGenresModel
import com.lesadisa.videoonlinecinema.data.api.model.CinemaOnlineResultsModel
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.domain.model.CinemaSourseDomainModel

fun CinemaOnlineResultsGenresModel.toDomain(): CinemaSourseDomainModel =
    CinemaSourseDomainModel(
        id = id,
        name = name
    )

fun CinemaOnlineResultsModel.toDomain(): CinemaDomainModel =
    CinemaDomainModel(
        adult = adultOnlain,
        genres = genresOnline.toDomain(),
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
