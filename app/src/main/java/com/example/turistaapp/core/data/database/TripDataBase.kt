package com.example.turistaapp.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.turistaapp.core.data.database.dao.TripDao
import com.example.turistaapp.core.data.database.entities.Converters
import com.example.turistaapp.core.data.database.entities.TripEntity

@Database(
    entities = [
        TripEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class TripDataBase : RoomDatabase() {

    abstract fun getTripDao(): TripDao
}
