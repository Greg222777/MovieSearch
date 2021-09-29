package com.nowik.moviesearch

object Utils {
    fun formatImagePath(path : String) : String {
        return ImdbApiClient.IMAGES_BASE_URL + path
    }
}