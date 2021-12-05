package com.lesadisa.videoonlinecinema.navigation

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

class CinemaNavigator(activity: FragmentActivity, containerId: Int) :
    AppNavigator(activity, containerId) {
    override fun applyCommands(commands: Array<out Command>) {
        super.applyCommands(commands)
        activity.supportFragmentManager.executePendingTransactions()
    }

/*    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out,

        )
    }*/
}
