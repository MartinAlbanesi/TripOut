package com.example.turistaapp.setting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.setting.domain.UpdateImagesFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    private val updateImagesFromDBUseCase : UpdateImagesFromDBUseCase,
    private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _darkTheme = MutableLiveData<Boolean>(true)
      val darkTheme: LiveData<Boolean> = _darkTheme

    private val _listLanguage = listOf<String>("Español" , "Ingles" , "Portuges")
    val listLanguage = _listLanguage


    fun changeTheme() {
        _darkTheme.value = !_darkTheme.value!!
    }

    fun updateImages(id: Int, images: List<String>) {
        viewModelScope.launch(dispatcher) {
            updateImagesFromDBUseCase(id, images)
        }
    }
}