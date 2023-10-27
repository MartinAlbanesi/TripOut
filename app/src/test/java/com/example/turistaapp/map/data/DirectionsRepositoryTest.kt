package com.example.turistaapp.map.data

import com.example.turistaapp.map.data.api.service.DirectionsApiService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before

class DirectionsRepositoryTest{

    private lateinit var directionsRepository: DirectionsRepository

    @RelaxedMockK
    private lateinit var directionsApiService: DirectionsApiService

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        directionsRepository = DirectionsRepository(directionsApiService)
    }

}