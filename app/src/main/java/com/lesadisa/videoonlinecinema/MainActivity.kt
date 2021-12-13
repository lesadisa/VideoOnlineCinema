package com.lesadisa.videoonlinecinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.lesadisa.videoonlinecinema.navigation.CinemaNavigator
import com.lesadisa.videoonlinecinema.navigation.Screens
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: Navigator = CinemaNavigator(this, R.id.fragmentContainerView)
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.cinemaCatalog())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }



}