package com.eduardandroid.wembleymoviesapp.usecase.screen.adapter.adapterlistmovies

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.eduardandroid.wembleymoviesapp.R
import com.eduardandroid.wembleymoviesapp.commons.IMAGE_URL
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.databinding.ItemMovieBinding
import javax.inject.Inject

class AdapterListMovies @Inject constructor():
    RecyclerView.Adapter<AdapterListMovies.ViewHolder>() {

    private lateinit var binding: ItemMovieBinding
    private var mListItem: MutableList<MovieBody> = mutableListOf()
    private lateinit var mContext: Context
    var onClickFavoriteMovie: (MovieBody) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_movie,
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListItem[position], position)
    }

    override fun getItemCount(): Int {
        return mListItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItems: MutableList<MovieBody>?, refreshList: Boolean) {
        if (refreshList) mListItem.clear()
        listItems?.let { mListItem.addAll(it) }
        notifyDataSetChanged()
    }

    fun getPositionForMovie(mMovie: Int): Int {
        val movies = mListItem
        for (i in movies.indices) {
            val movie = movies[i]
            if (movie.idMovie == mMovie) {
                return i
            }
        }
        return RecyclerView.NO_POSITION
    }

    fun removeSelectionFavorite(position: Int, movie: MovieBody) {
        mListItem.removeAt(position)
        mListItem.add(position, movie)
        notifyItemChanged(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataSearch(movieBodies: MutableList<MovieBody>) {
        mListItem.clear()
        movieBodies.let { mListItem.addAll(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieBody: MovieBody, position: Int) {
            if (movieBody.posterPath != null) {
                val imagePath = "$IMAGE_URL${movieBody.posterPath}"
                Glide.with(mContext)
                    .load(imagePath)
                    .placeholder(ColorDrawable(ContextCompat.getColor(mContext, R.color.dark_gray)))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivMovie)
            }
            if (movieBody.isFavorite == true) {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_700));
            } else {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(mContext, R.color.white));
            }
            binding.ivFavorite.setOnClickListener {
                movieBody.isFavorite = movieBody.isFavorite == false
                notifyItemChanged(position)
                onClickFavoriteMovie.invoke(movieBody)
            }
        }
    }
}