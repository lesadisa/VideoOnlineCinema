package com.lesadisa.videoonlinecinema.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lesadisa.videoonlinecinema.domain.model.CinemaDomainModel
import com.lesadisa.videoonlinecinema.features.cinema_card.ui.CinemaCardFragment
import com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui.CinemaCatalogFragment
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui.MoviePlayerFragment

object Screens {

    fun cinemaCatalog() = FragmentScreen {
        CinemaCatalogFragment.newInstance()
    }

    fun cinemaCard(cinema: CinemaDomainModel) = FragmentScreen {
        CinemaCardFragment.newInstance(cinema)
    }

    fun moviePlayer(videoUrl: String) = FragmentScreen {
        MoviePlayerFragment.newInstance(videoUrl)
    }


}