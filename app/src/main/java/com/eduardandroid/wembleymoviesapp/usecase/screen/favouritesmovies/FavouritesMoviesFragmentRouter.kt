package com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eduardandroid.wembleymoviesapp.usecase.base.BaseFragmentRouter
import javax.inject.Inject

class FavouritesMoviesFragmentRouter @Inject constructor(): BaseFragmentRouter {

    private var instance: FavouritesMoviesFragment? = null

    override fun fragment(): FavouritesMoviesFragment {
        instance = FavouritesMoviesFragment.newInstance()
        return instance!!
    }
}