package com.example.turistaapp.setting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import com.example.turistaapp.setting.domain.UpdateImagesFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    private val updateImagesFromDBUseCase: UpdateImagesFromDBUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val localDataStoreRepository: LocalDataStoreRepository
) : ViewModel() {

    private val _darkTheme = MutableLiveData<Boolean>(true)
    val darkTheme: LiveData<Boolean> = _darkTheme

    init {
        viewModelScope.launch(Dispatchers.Main) {
            localDataStoreRepository.getIsDarkMode().collect {
                if (it != null) {
                    _darkTheme.value = it
                }
            }
        }
    }


    fun changeTheme() {
        _darkTheme.value = !_darkTheme.value!!
        viewModelScope.launch(Dispatchers.Main) {
            localDataStoreRepository.setIsDarkMode(_darkTheme.value!!)
        }
    }

    fun updateImages(id: Int, images: List<String>) {
        viewModelScope.launch(dispatcher) {
            updateImagesFromDBUseCase(id, images)
        }
    }
}