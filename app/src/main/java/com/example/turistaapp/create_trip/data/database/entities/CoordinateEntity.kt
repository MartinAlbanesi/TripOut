package com.example.turistaapp.create_trip.data.database.entities

import androidx.room.ColumnInfo

data class CoordinateEntity(

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double
)
