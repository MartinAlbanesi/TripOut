package com.example.turistaapp.setting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class SettingViewModel:ViewModel() {

    private val _darkTheme = MutableLiveData<Boolean>(false)
      val darkTheme: LiveData<Boolean> = _darkTheme

    fun changeTheme() {
     _darkTheme.value = !_darkTheme.value!!
    }

}