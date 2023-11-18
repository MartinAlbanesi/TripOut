package com.example.turistaapp.welcome.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SetNameFromDataStoreTest{

    @RelaxedMockK
    private lateinit var dataStore: LocalDataStoreRepository

    private lateinit var setNameFromDataStore: SetNameFromDataStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        setNameFromDataStore = SetNameFromDataStore(dataStore)
    }

    @Test
    fun `invoke - Must setting a name - When invoke is calling` () = runTest{
        val expected = "titi"

        coEvery { dataStore.getName() } returns flowOf(expected)

        setNameFromDataStore("titi")

        val actual = dataStore.getName()

        assertEquals(expected, actual.first())
    }

}