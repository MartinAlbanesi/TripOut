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

class GetNameFromDataStoreTest{

    private lateinit var getNameFromDataStore: GetNameFromDataStore

    @RelaxedMockK
    private lateinit var dataStore: LocalDataStoreRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getNameFromDataStore = GetNameFromDataStore(dataStore)
    }

    @Test
    fun `invoke - Must return Name - When dataStore getName is calling` () = runTest{

        val expected = flowOf("Titi")

        coEvery { dataStore.getName() } returns expected

        val result = getNameFromDataStore()

        assertEquals(expected.first(), result.first())
    }

    @Test
    fun `invoke - Must return null - When dataStore getName return flow with null string` () = runTest{

        val expected = flowOf(null)

        coEvery { dataStore.getName() } returns expected

        val result = getNameFromDataStore()

        assertEquals(expected.first(), result.first())
    }

}