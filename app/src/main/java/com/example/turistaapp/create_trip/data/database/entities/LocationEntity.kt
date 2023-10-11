package com.example.turistaapp.create_trip.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "place_id")
    val placeID: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "address")
    val address: String?,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "user_rating")
    val userRating: Int,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "photo")
    val photo: String?,

    @ColumnInfo(name = "types")
    val types: List<String>?,
)
