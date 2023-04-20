package com.eduardandroid.wembleymoviesapp.usecase.screen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies.FavouritesMoviesFragment
import com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies.FavouritesMoviesFragmentRouter
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragment
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragmentRouter
import javax.inject.Inject

class CustomMoviesPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    listMoviesFragmentRouter:
    ListMoviesFragmentRouter,
    favouritesMoviesFragmentRouter: FavouritesMoviesFragmentRouter
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = listOf(listMoviesFragmentRouter.fragment(), favouritesMoviesFragmentRouter.fragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}