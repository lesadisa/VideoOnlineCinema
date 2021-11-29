package com.lesadisa.videoonlinecinema.di


import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.lesadisa.videoonlinecinema.Const.HttpConst.BASE_MOVIES_URL
import com.lesadisa.videoonlinecinema.base.httpCache10Mb
import com.lesadisa.videoonlinecinema.base.okHttp
import com.lesadisa.videoonlinecinema.data.api.CinemaApi
import com.lesadisa.videoonlinecinema.data.api.CinemaRemoteSource
import com.lesadisa.videoonlinecinema.data.api.CinemaRepository
import com.lesadisa.videoonlinecinema.data.api.CinemaRepositoryImpl
import com.lesadisa.videoonlinecinema.domain.CinemaInteractor
import com.lesadisa.videoonlinecinema.features.cinema_catalog_screen.ui.CinemaCatalogViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATA_BASE = "DATA_BASE"
val appModule = module {
    single<OkHttpClient> {
        okHttp(cache = httpCache10Mb(androidContext()))
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_MOVIES_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
val navModule = module {
    single<Cicerone<Router>> {
        Cicerone.create(Router())
    }

    single<NavigatorHolder> {
        get<Cicerone<Router>>().getNavigatorHolder()
    }

    single<Router> {
        get<Cicerone<Router>>().router
    }

    single<CinemaApi> {
        get<Retrofit>().create(CinemaApi::class.java)
    }

    single<CinemaRemoteSource> {
        CinemaRemoteSource(get<CinemaApi>())
    }
    single<CinemaRepository> {
        CinemaRepositoryImpl(get<CinemaRemoteSource>())
    }

    single<CinemaInteractor> {
        CinemaInteractor(get<CinemaRepository>())
    }

    viewModel<CinemaCatalogViewModel> {
        CinemaCatalogViewModel(
            get<CinemaInteractor>(),
            get<Router>()
        )
    }




}

