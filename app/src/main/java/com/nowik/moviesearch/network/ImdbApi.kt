package com.nowik.moviesearch.network

import androidx.lifecycle.LiveData
import com.nowik.moviesearch.model.Movie
import com.nowik.moviesearch.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImdbApi {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") search: String
    ): SearchResult

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String

    ): Movie
}