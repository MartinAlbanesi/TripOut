package com.example.turistaapp.core.utils

fun getPhotosFromMap(imageUrl: String): String {
    return "https://maps.googleapis.com/maps/api/place/photo?" +
        "maxwidth=400" +
        "&key=AIzaSyCPj0hmVS-0SKTDWGFNCgPdkQSFuQnD4Ps" +
        "&photo_reference=$imageUrl"
}
