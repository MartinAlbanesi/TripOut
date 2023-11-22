package com.example.turistaapp.welcome.ui

import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WelcomeViewModelTest{
    
    private lateinit var welcomeViewModel: WelcomeViewModel

    @RelaxedMockK
    private lateinit var setNameFromDataStore : SetNameFromDataStore
    
    @RelaxedMockK
    private lateinit var getNameFromDataStore: GetNameFromDataStore

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()
    
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        
        welcomeViewModel = WelcomeViewModel(
            setNameFromDataStore,
            getNameFromDataStore,
            dispatcher
        )
    }
    
    @Test
    fun `init - name Must return a name - When getNameFromDataStore return not null` () = runTest{
        val expected = flow{
            emit("titi")
        }

        coEvery { getNameFromDataStore.invoke() } returns expected

        val actual = welcomeViewModel.name.value

        assertEquals(expected.first(), actual)
    }

    @Test
    fun `init - name Must return null - When getNameFromDataStore return null` () = runTest{
        val expected = flow{
            emit(null)
        }

        coEvery { getNameFromDataStore.invoke() } returns expected

        val actual = welcomeViewModel.name.value

        assertEquals(expected.first(), actual)
    }
    
}