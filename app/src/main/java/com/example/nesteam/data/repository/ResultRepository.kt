package com.example.nesteam.data.repository

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

}