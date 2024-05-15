package com.example.nesteam.data.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortScreenshot(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val imageLink: String
) : Parcelable