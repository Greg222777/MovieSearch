package com.nowik.moviesearch

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nowik.moviesearch.databinding.SearchResultItemBinding
import com.nowik.moviesearch.model.Movie

class SearchResultsAdapter(val onMovieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    var movies = listOf<Movie>()

    inner class ViewHolder(private val binding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.releaseDate.text = movie.releaseDate

            /**
             * Pas la bonne taille
             */

            if (!movie.posterPath.isNullOrEmpty()) {
                Glide.with(binding.posterImageView.context)
                    .load(Utils.formatImagePath(movie.posterPath))
                    .into(binding.posterImageView)
            }

            binding.root.setOnClickListener {
                onMovieClickListener.onMovieClicked(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(movies[position])
    }

    interface OnMovieClickListener {
        fun onMovieClicked(movieId: Int)
    }
}