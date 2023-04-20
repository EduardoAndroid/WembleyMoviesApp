package com.eduardandroid.wembleymoviesapp.usecase.screen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies.FavouritesMoviesFragment
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragment

class CustomMoviesPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = listOf(ListMoviesFragment(), FavouritesMoviesFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}