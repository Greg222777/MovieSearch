package com.nowik.moviesearch.model

data class SearchResult(
    val page : Int,
    val results : List<Movie>
)