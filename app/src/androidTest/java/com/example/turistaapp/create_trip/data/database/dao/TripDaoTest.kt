package com.example.turistaapp.create_trip.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.turistaapp.core.database.TripDataBase
import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.data.database.entities.LocationEntity
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TripDaoTest {

    private lateinit var tripDao: TripDao
    private lateinit var db : TripDataBase
    private lateinit var gson : Gson

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,TripDataBase::class.java)
            .build()
        tripDao = db.getTripDao()

        gson = Gson()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertTripListAndReadTripsInserted() = runTest {
        val location = LocationEntity(
            1,
            "Location 1",
            "Description 1",
            null,
            0.0,
            1,
            1.1,
            2.2,
            null,
            null,
        )
        val expected = listOf(
            TripEntity(
                id = 1,
                name = "Trip 1",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 1",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
            TripEntity(
                id = 2,
                name = "Trip 2",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 2",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
            TripEntity(
                id = 3,
                name = "Trip 3",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 3",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
        )

        tripDao.insertTripList(expected)

        val actual = tripDao.getTripList()

        assertEquals(expected,actual)
        assertEquals(expected[0].name,actual[0].name)
    }

    @Test
    fun getLocationsFromDestination_returnLocationsList() = runTest{
        val location = LocationEntity(
            1,
            "Location 1",
            "Description 1",
            null,
            0.0,
            1,
            1.1,
            2.2,
            null,
            null,
        )
        val expected = listOf(
            TripEntity(
                id = 1,
                name = "Trip 1",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 1",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
            TripEntity(
                id = 2,
                name = "Trip 2",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 2",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
            TripEntity(
                id = 3,
                name = "Trip 3",
                origin = location,
                destination = location,
                stops = null,
                startDate = "2021-01-01",
                endDate = "2021-01-02",
                members = null,
                transport = "Transport 3",
                description = null,
                author = "",
                isFavorite = false,
                isFinished = false,
                images = null,
                comments = null,
            ),
        )

        tripDao.insertTripList(expected)

        val actual = tripDao.getLocationsFromDestination().map {
            GsonConverter.fromJson(it,LocationEntity::class.java)
        }

        assertEquals(location.name,actual[0].name)
    }

    @Test
    fun getLocationsFromDestination_whenDestinationIsEmpty_returnEmptyList() = runTest {

        val actual = tripDao.getLocationsFromDestination()

        assertEquals(emptyList<Any>(), actual)
    }
}