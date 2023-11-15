package com.example.turistaapp.create_trip.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTripList(trip: List<TripEntity>)

    @Query("SELECT * FROM trips_table ORDER BY name DESC")
    fun getAllTrips(): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips_table")
    suspend fun getTripList(): List<TripEntity>

    @Query("SELECT destination FROM trips_table")
    suspend fun getLocationsFromDestination(): List<String>

    @Query("SELECT destination FROM trips_table")
    fun getFlowLocationsFromDestination(): Flow<List<String>>

    @Delete
    fun deleteTrip(trip: TripEntity)

    @Query("UPDATE trips_table SET images = :images WHERE id = :id")
    fun updateImages(id: Int, images: List<String>)
}
