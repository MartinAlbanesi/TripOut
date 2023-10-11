package com.example.turistaapp.create_trip.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips_table")
data class TripEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "origin")
    val origin: LocationEntity,

    @ColumnInfo(name = "destination")
    val destination: LocationEntity,

    @ColumnInfo(name = "stops")
    val stops: MutableList<LocationEntity>?,

    @ColumnInfo(name = "startDate")
    val startDate: String,

    @ColumnInfo(name = "endDate")
    val endDate: String,

    @ColumnInfo(name = "members")
    val members: MutableList<String>?,

    @ColumnInfo(name = "transport")
    val transport: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "isFinished")
    val isFinished: Boolean,

    @ColumnInfo(name = "images")
    val images: MutableList<String>?,

    @ColumnInfo(name = "comments")
    val comments: MutableList<String>?
)
