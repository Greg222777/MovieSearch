package com.nowik.moviesearch

import com.nowik.moviesearch.network.ImdbApiClient

object Utils {

    // api only returns the ID and the base url needs to be addedd manually
    fun formatImagePath(path : String) : String {
        return ImdbApiClient.IMAGES_BASE_URL + path
    }
}