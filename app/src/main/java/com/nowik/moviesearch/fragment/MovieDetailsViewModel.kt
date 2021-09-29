package com.nowik.moviesearch.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nowik.moviesearch.ImdbApiClient
import com.nowik.moviesearch.model.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    var movieLiveData = MutableLiveData<Movie>()

    fun getMovie(movieId: Int) = viewModelScope.launch {
        val movie = ImdbApiClient.getInstance().getMovie( movieId, ImdbApiClient.API_KEY)
        movieLiveData.postValue(movie)
    }
}