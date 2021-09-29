package com.nowik.moviesearch

import androidx.lifecycle.LiveData
import com.nowik.moviesearch.model.Movie
import com.nowik.moviesearch.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImdbApi {

    /**
     * Bug étrange avec le /3/ qui ne fonctionne pas dans la base url ?  Obligé de le mettre ici.
     * Abandonné le problème parce que j'ai perdu beaucoup trop de temps avec ceci
     * Aussi, l'API KEY pourrait être ajoutée avec un interceptor à tous les appels d'API dans
     * le singleton retrofit, ça serait plus propre que de la demander à chaque appel
     */

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") search: String
    ): SearchResult

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String

    ): Movie
}