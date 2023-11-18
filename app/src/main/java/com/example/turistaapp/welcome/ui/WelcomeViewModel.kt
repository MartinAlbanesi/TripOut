package com.example.turistaapp.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val setNameFromDataStore : SetNameFromDataStore,
    private val getNameFromDataStore: GetNameFromDataStore,
    private val dispatcher: CoroutineDispatcher
) : ViewModel(){

    private val _name = MutableStateFlow<String?>(null /*"titi"*/)
    val name = _name.asStateFlow()

    init {
        getNameFromDataStore()
    }

    fun setNameInDataStore(name : String) {
        viewModelScope.launch(dispatcher) {
            setNameFromDataStore(name)
        }
    }

    private fun getNameFromDataStore() {
        viewModelScope.launch(dispatcher) {
            getNameFromDataStore.invoke().collect{
                 _name.value = it
            }
        }
    }



}