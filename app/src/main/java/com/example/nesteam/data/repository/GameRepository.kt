package com.example.nesteam.data.repository

import android.util.Log
import com.example.nesteam.data.model.entity.Game
import com.example.nesteam.data.network.ApiServiceInterface

class GameRepository(private val apiService: ApiServiceInterface) {

    suspend fun getGameById(gameId: Int): Game? {
        try {
            val response = apiService.getGameById(gameId, "ad61cc3d0cf246ffb16f9f76affe0844")
            if (response.isSuccessful) {
                return response.body()
            } else {
                // Log error or handle the error response
                Log.e("GameRepository", "Failed to fetch game: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            // Handle exceptions like network issues
            Log.e("GameRepository", "Exception when fetching game", e)
        }
        return null
    }

}