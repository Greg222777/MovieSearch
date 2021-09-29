package com.nowik.moviesearch.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nowik.moviesearch.network.ImdbApiClient
import com.nowik.moviesearch.model.Movie
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    var searchResultLiveData = MutableLiveData<List<Movie>>()

     fun searchMovies(query : String) {
         viewModelScope.launch {
             val result = ImdbApiClient.getInstance().searchMovies(ImdbApiClient.API_KEY, query).results
             searchResultLiveData.postValue(result)
         }
    }
}