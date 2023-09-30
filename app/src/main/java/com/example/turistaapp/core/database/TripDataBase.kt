package com.example.turistaapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.turistaapp.create_trip.data.database.dao.TripDao
import com.example.turistaapp.create_trip.data.database.entities.Converters
import com.example.turistaapp.create_trip.data.database.entities.TripEntity

@Database(
    entities = [
        TripEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters( Converters::class )
abstract class TripDataBase: RoomDatabase() {

    abstract fun getTripDao(): TripDao
}