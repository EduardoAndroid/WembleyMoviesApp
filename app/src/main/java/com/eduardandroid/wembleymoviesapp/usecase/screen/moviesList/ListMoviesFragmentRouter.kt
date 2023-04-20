package com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eduardandroid.wembleymoviesapp.usecase.base.BaseFragmentRouter
import javax.inject.Inject

class ListMoviesFragmentRouter @Inject constructor(): BaseFragmentRouter {

    private var instance: ListMoviesFragment? = null

    override fun fragment(): ListMoviesFragment {
        instance = ListMoviesFragment.newInstance()
        return instance!!
    }
}