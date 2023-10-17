package com.example.turistaapp.create_trip.data.database.dao // ktlint-disable package-name

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.turistaapp.core.database.TripDataBase
import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.FakeDataBaseSource
import com.example.turistaapp.create_trip.data.database.entities.LocationEntity
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TripDaoTest {

    private lateinit var tripDao: TripDao
    private lateinit var db: TripDataBase
    private lateinit var gson: Gson

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TripDataBase::class.java)
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
        val expected = FakeDataBaseSource.tripEntityList

        tripDao.insertTripList(expected)

        val actual = tripDao.getTripList()

        assertEquals(expected, actual)
        assertEquals(expected[0].name, actual[0].name)
    }

    @Test
    fun getLocationsFromDestination_returnLocationsList() = runTest {
        val expected = FakeDataBaseSource.locationEntity
        val tripEntityList = FakeDataBaseSource.tripEntityList

        tripDao.insertTripList(tripEntityList)

        val actual = tripDao.getLocationsFromDestination().map {
            GsonConverter.fromJson(it, LocationEntity::class.java)
        }

        assertEquals(expected.name, actual[0].name)
    }

    @Test
    fun getLocationsFromDestination_whenDestinationIsEmpty_returnEmptyList() = runTest {
        val actual = tripDao.getLocationsFromDestination()

        assertEquals(emptyList<Any>(), actual)
    }

    @Test
    fun getFlowLocationsFromDestination_returnLocationsList() = runTest {
        val expected = FakeDataBaseSource.locationEntity
        val tripEntityList = FakeDataBaseSource.tripEntityList

        tripDao.insertTripList(tripEntityList)

        val actual = tripDao.getFlowLocationsFromDestination().first().map {
            GsonConverter.fromJson(it, LocationEntity::class.java)
        }
        assertEquals(expected.name, actual[0].name)
    }

    @Test
    fun getFlowLocationsFromDestination_whenDestinationIsEmpty_returnLocationsList() = runTest {
        val actual = tripDao.getFlowLocationsFromDestination().first()

        assertTrue(actual.isEmpty())
        assertEquals(emptyList<Any>(), actual)
    }
}
