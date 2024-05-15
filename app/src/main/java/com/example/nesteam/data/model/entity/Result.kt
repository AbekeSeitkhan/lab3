package com.example.nesteam.data.model.entity

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Result(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("released") val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("metacritic") val metacritic: Int,
    @SerializedName("esrb_rating") val esrbRating: EsrbRating?,
    @SerializedName("genres") val genres: List<Genre>
) : Parcelable, Serializable

