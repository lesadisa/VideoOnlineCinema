package com.lesadisa.videoonlinecinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lesadisa.videoonlinecinema.features.films_screen.ui.CinemaListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(CinemaListFragment())
    }

    private fun setFragment(fragment: Fragment) {
        // commit обязателен
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()
    }
}