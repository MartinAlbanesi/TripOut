package com.example.turistaapp.map.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetIsMapTutorialCompleteTest{

    @RelaxedMockK
    private lateinit var localDataStoreRepository: LocalDataStoreRepository

    private lateinit var getIsMapTutorialComplete: GetIsMapTutorialComplete

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getIsMapTutorialComplete = GetIsMapTutorialComplete(localDataStoreRepository)
    }

    @Test
    fun `invoke - Must return a boolean - When getIsMapTutorialComplete from localDataStoreRepository is not null` () = runTest{

        val expected = true

        coEvery { localDataStoreRepository.getIsMapTutorialComplete() } returns flowOf(expected)

        val result = getIsMapTutorialComplete()

        assertNotNull(result.first())
        assertEquals(expected, result.first())
    }

    @Test
    fun `invoke - Must return a flow null - When getIsMapTutorialComplete from localDataStoreRepository is null` () = runTest{

        val expected = null

        coEvery { localDataStoreRepository.getIsMapTutorialComplete() } returns flowOf(expected)

        val result = getIsMapTutorialComplete()

        assertNull(result.first())
        assertEquals(expected, result.first())
    }

}