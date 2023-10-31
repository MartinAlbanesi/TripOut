package com.example.turistaapp.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val setNameFromDataStore : SetNameFromDataStore,
    private val dispatcher: CoroutineDispatcher
) : ViewModel(){

    fun setNameInDataStore(name : String) {
        viewModelScope.launch(dispatcher) {
            setNameFromDataStore(name)
        }
    }

}