package com.example.nesteam.data.model.entity

import com.google.gson.annotations.SerializedName


data class GameResponse(
    val count: Int,
    @SerializedName("results") val results: List<Result>
)