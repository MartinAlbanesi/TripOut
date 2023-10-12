package com.example.turistaapp.home.data.api.model

import com.google.gson.annotations.SerializedName

data class PhotoApi(
    val height: Int,
    @SerializedName("photo_reference") val photoUrl: String,
    val width: Int
)