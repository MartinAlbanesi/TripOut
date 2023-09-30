package com.example.turistaapp.create_trip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.turistaapp.create_trip.data.database.dao.TripDao
import com.example.turistaapp.create_trip.data.database.entities.TripEntity

@Database(
    entities = [
        TripEntity::class
    ],
    version = 1
)
abstract class TripDataBase: RoomDatabase() {

    abstract fun getTripDao(): TripDao
}