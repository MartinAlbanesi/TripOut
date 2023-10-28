package com.example.turistaapp.welcome.ui

import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getNameFromDataStore : GetNameFromDataStore,
    private val setNameFromDataStore : SetNameFromDataStore
) {
}