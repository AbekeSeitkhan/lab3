package com.example.nesteam.data.network

import com.example.nesteam.data.model.entity.Game
import com.example.nesteam.data.model.entity.GameResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("games")
    suspend fun getGames(@Query("key") apiKey: String): Response<GameResponse>

    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") gameId:Int,
        @Query("key") apiKey: String
    ):Response<Game>
}