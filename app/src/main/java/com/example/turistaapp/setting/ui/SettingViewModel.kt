package com.example.turistaapp.setting.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val localDataStoreRepository: LocalDataStoreRepository,
    private val getNameFromDataStore: GetNameFromDataStore,
    private val setNameFromDataStore: SetNameFromDataStore,
) : ViewModel() {

    private val _darkTheme = MutableStateFlow(true)
    val darkTheme = _darkTheme.asStateFlow()

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _currentLanguage = MutableStateFlow("es")
    val currentLanguage = _currentLanguage.asStateFlow()

    init {
        getIsDarkMode()
        getUserName()
        getCurrentLanguage()
    }

    private fun getCurrentLanguage() {
        viewModelScope.launch(dispatcher) {
            _currentLanguage.value = localDataStoreRepository.getLanguage()
        }
    }

    fun setCurrentLanguage(codeLanguage: String) {
        viewModelScope.launch(dispatcher) {
            _currentLanguage.value = codeLanguage
        }
    }

    private fun getUserName() {
        viewModelScope.launch(dispatcher) {
            getNameFromDataStore.invoke().collect {
                _userName.value = it
            }
        }
    }

    fun setUserName(name: String) {
        viewModelScope.launch(dispatcher) {
            setNameFromDataStore(name)
        }
    }

    private fun getIsDarkMode() {
        viewModelScope.launch(Dispatchers.Main) {
            localDataStoreRepository.getIsDarkMode().collect {
                if (it != null) {
                    _darkTheme.value = it
                }
                _isLoading.value = false
            }
        }
    }

    fun changeTheme() {
        _darkTheme.value = !_darkTheme.value
        viewModelScope.launch(Dispatchers.Main) {
            localDataStoreRepository.setIsDarkMode(_darkTheme.value)
        }
    }
}
