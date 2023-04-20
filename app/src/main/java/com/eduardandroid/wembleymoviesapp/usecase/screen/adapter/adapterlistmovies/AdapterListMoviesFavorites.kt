package com.eduardandroid.wembleymoviesapp.usecase.screen.adapter.adapterlistmovies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardandroid.wembleymoviesapp.R
import com.eduardandroid.wembleymoviesapp.data.model.MovieBody
import com.eduardandroid.wembleymoviesapp.databinding.ItemMovieBinding
import com.eduardandroid.wembleymoviesapp.provider.room.roomListMovies.MovieEntity
import javax.inject.Inject

class AdapterListMoviesFavorites @Inject constructor():
    RecyclerView.Adapter<AdapterListMoviesFavorites.ViewHolder>() {

    private lateinit var binding: ItemMovieBinding
    private var mListItem: MutableList<MovieBody> = mutableListOf()
    private lateinit var mContext: Context
    var onClickFavoriteMovie: (MovieBody, Int) -> Unit = {movieBody, position ->  }

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
    fun setData(listItems: MutableList<MovieBody>?) {
        mListItem.clear()
        listItems?.let { mListItem.addAll(it) }
        notifyDataSetChanged()
    }

    fun removeMovie(movieBody: MovieBody, position: Int) {
        mListItem.remove(movieBody)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieBody: MovieBody, position: Int) {
            if (movieBody.posterPath != null) {
                val imagePath = "https://image.tmdb.org/t/p/w500/${movieBody.posterPath}"
                Glide.with(mContext).load(imagePath).into(binding.ivMovie)
            }
            if (movieBody.isFavorite == true) {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_200));
            } else {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(mContext, R.color.white));
            }
            binding.ivFavorite.setOnClickListener {
                movieBody.isFavorite = movieBody.isFavorite == false
                notifyItemChanged(position)
                onClickFavoriteMovie.invoke(movieBody, position)
            }
        }
    }
}