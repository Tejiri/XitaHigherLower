package com.xita.games.higherlower.dataSource.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    private const val BASE_URL = "https://deckofcardsapi.com/api/deck/"
//
    val cardAPI: CardAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardAPI::class.java)
    }

}