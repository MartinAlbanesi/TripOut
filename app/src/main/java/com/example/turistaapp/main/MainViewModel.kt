package com.example.turistaapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _indexSelect = MutableLiveData(1)
    val indexSelect : LiveData<Int> = _indexSelect

    private val _titleAppBar = MutableLiveData("Home")
    val titleAppBar : LiveData<String> = _titleAppBar

    fun setIndex(index : Int){
        _indexSelect.value = index
    }

    fun setTitle(title : String){
        _titleAppBar.value = title
    }

}