package com.example.turistaapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.turistaapp.core.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    private val _indexSelect = MutableLiveData(1)
    val indexSelect : LiveData<Int> = _indexSelect

    private val _titleAppBar = MutableLiveData("Home")
    val titleAppBar : LiveData<String> = _titleAppBar

    private val _showBottomBar = MutableLiveData(true)
    val showBottomBar : LiveData<Boolean> = _showBottomBar

    private val _showFloatingActionButton = MutableLiveData(true)
    val showFloatingActionButton : LiveData<Boolean> = _showFloatingActionButton

    private val _route = MutableLiveData(Routes.Home.route)
    val route : LiveData<String> = _route

    fun setRoute(route : String){
        _route.value = route
    }

    fun setIndex(index : Int){
        _indexSelect.value = index
    }

    fun setTitle(title : String){
        _titleAppBar.value = title
    }

    fun setShowBottomBar(show : Boolean){
        _showBottomBar.value = show
    }

    fun setShowFloatingActionButton(show : Boolean){
        _showFloatingActionButton.value = show
    }

}