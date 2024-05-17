package com.example.nesteam.data.repository

import android.util.Log
import com.example.nesteam.data.network.ApiServiceInterface
import com.example.nesteam.data.model.entity.Result

class ResultRepository(private val apiService: ApiServiceInterface) {

    suspend fun getGames(): List<Result> {
        val response = apiService.getGames("ad61cc3d0cf246ffb16f9f76affe0844")
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        }
        return emptyList()
    }

    suspend fun searchGames(query: String): List<Result> {
        val response = apiService.searchGames("ad61cc3d0cf246ffb16f9f76affe0844", query)
        if (response.isSuccessful) {
            response.body()?.let {
                Log.d("SearchFragment", "API Response successful")
                return it.results ?: emptyList()
            }
        } else {
            Log.e("SearchFragment", "API Response Error: ${response.errorBody()?.string()}")
        }
        return emptyList()
    }

}