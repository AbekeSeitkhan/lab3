package com.example.nesteam.data.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Game(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("released") val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("metacritic") val metacritic: Int,
    @SerializedName("esrb_rating") val esrbRating: EsrbRating?,
    @SerializedName("short_screenshots") val shortScreenshots: List<ShortScreenshot>,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("description") val description: String,
    @SerializedName("website") val website: String
) : Parcelable, Serializable
