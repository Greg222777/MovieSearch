package com.nowik.moviesearch

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImdbApiClient {
    private var instance: ImdbApi? = null

    private const val BASE_URL = "https://api.themoviedb.org/3/"
     const val API_KEY = "b2168bae3a2c67509eb6b97572f521c2"

    @Synchronized
    fun getInstance(): ImdbApi {
        if (instance == null) {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            instance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
                .create(ImdbApi::class.java)
        }

        return instance as ImdbApi
    }
}