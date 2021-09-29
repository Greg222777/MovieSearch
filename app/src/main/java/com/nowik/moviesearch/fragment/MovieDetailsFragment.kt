package com.nowik.moviesearch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nowik.moviesearch.databinding.FragmentMovieDetailsBinding
import com.nowik.moviesearch.model.Movie

class MovieDetailsFragment : Fragment() {

    companion object {
        const val ARGS_MOVIE_ID = "args_movie_id"
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        movie = arguments?.getParcelable(ARGS_MOVIE_ID) as? Movie
        return binding.root
    }


}