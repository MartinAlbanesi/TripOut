package com.example.turistaapp.create_trip.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "address")
    val address: String?,

    @ColumnInfo(name = "coordinate")
    val coordinate: CoordinateEntity,

    @ColumnInfo(name = "photo")
    val photo: String?,

    @ColumnInfo(name = "types")
    val types: List<String>?,

    //val rating: Double
)
