package com.nowik.moviesearch

import com.nowik.moviesearch.network.ImdbApiClient

object Utils {
    fun formatImagePath(path : String) : String {
        return ImdbApiClient.IMAGES_BASE_URL + path
    }
}