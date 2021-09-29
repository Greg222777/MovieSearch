package com.nowik.moviesearch.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nowik.moviesearch.R
import com.nowik.moviesearch.Utils
import com.nowik.moviesearch.databinding.FragmentMovieDetailsBinding
import com.nowik.moviesearch.model.Movie


class MovieDetailsFragment : Fragment() {

    companion object {
        const val ARGS_MOVIE_ID = "args_movie_id"
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovie()
    }

    private fun getMovie() {
        val movieId: Int? = arguments?.getInt(ARGS_MOVIE_ID)

        viewModel.movieLiveData.observe(viewLifecycleOwner) { movie ->
            bindMovie(movie)
        }

        if (movieId != null)
            viewModel.getMovie(movieId)
    }

    private fun bindMovie(movie: Movie) {

        // textes (XML binding)
        binding.movie = movie
        binding.executePendingBindings()

        // images
        if (!movie.posterPath.isNullOrEmpty()) {
            Glide.with(binding.posterImageView.context)
                .load(Utils.formatImagePath(movie.posterPath))
                .into(binding.posterImageView)
        }

        if (!movie.backdropPath.isNullOrEmpty()) {
            Glide.with(binding.posterImageView.context)
                .load(Utils.formatImagePath(movie.backdropPath))
                .into(binding.backdropImageView)
        }

        // rating
        // divide by 2 because rating bar has 5 stars and averageVote in Movie object is out of 10
        binding.ratingBar.rating = movie.voteAverage / 2

        // homepage
        binding.visitWebsiteButton.setOnClickListener {
            if (movie.homepage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.no_website), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.homepage))
                startActivity(browserIntent)
            }
        }
    }


}