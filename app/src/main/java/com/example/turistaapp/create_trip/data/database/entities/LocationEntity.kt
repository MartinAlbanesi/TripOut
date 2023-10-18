package com.example.turistaapp.create_trip.data.database.entities // ktlint-disable package-name

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

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lng")
    val lng: Double,

    @ColumnInfo(name = "photo")
    val photo: String?,

    @ColumnInfo(name = "types")
    val types: List<String>?,
)
