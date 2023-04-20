package com.eduardandroid.wembleymoviesapp.usecase.screen.moviegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.eduardandroid.wembleymoviesapp.R
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.databinding.ActivityMovieGalleryBinding
import com.eduardandroid.wembleymoviesapp.usecase.screen.adapter.CustomMoviesPagerAdapter
import com.eduardandroid.wembleymoviesapp.usecase.screen.favouritesmovies.FavouritesMoviesFragmentRouter
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragment
import com.eduardandroid.wembleymoviesapp.usecase.screen.moviesList.ListMoviesFragmentRouter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieGalleryActivity : AppCompatActivity(), OnMovieRemoveToFavoritesListener {

    private lateinit var binding: ActivityMovieGalleryBinding
    val viewModel: MovieGalleryViewModel by viewModels()

    private lateinit var mAdapter: CustomMoviesPagerAdapter

    @Inject
    lateinit var listMoviesFragmentRouter: ListMoviesFragmentRouter
    @Inject
    lateinit var favouritesMoviesFragmentRouter: FavouritesMoviesFragmentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_gallery)

        initAdapter()
        initListeners()
    }

    private fun initListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // No se necesita implementación
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // No se necesita implementación
            }
        })
    }

    private fun initAdapter() {
        binding.viewPager.apply {
            mAdapter = CustomMoviesPagerAdapter(fragmentManager = supportFragmentManager, lifecycle = lifecycle, listMoviesFragmentRouter, favouritesMoviesFragmentRouter)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                }
            })
        }
    }

    override fun onMovieAddedToFavorites(movie: MovieBody) {
        binding.viewPager.apply {
            val fragmentManager = supportFragmentManager
            (supportFragmentManager.findFragmentByTag("f" + 0) as ListMoviesFragment).refreshMovie(movie.idMovie, movie)
            //val fragmentUno = fragmentManager.findFragmentByTag("android:switcher:$this:0") as? ListMoviesFragment
            //fragmentUno?.refreshMovie(movie.idMovie)
        }
    }
}