package com.example.turistaapp.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _indexSelect = MutableLiveData(1)
    val indexSelect : LiveData<Int> = _indexSelect

    fun setIndex(index : Int){
        _indexSelect.value = index
    }

}