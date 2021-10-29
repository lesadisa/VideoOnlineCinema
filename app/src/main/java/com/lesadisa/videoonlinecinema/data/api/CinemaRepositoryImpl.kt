package com.lesadisa.videoonlinecinema.data.api

import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel

class CinemaRepositoryImpl(private val source: CinemaRemoteSource) :
    CinemaRepository {
    override suspend fun fetchList(): List<CinemaDomainModel> {
        return source.fetchList().resultsOnline.map { article -> article.toDomain() }
    }
}


