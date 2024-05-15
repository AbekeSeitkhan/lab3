package com.example.nesteam.data.network

import com.example.nesteam.data.model.entity.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("games")
    fun getGames(@Query("key") apiKey: String): Call<GameResponse>


}