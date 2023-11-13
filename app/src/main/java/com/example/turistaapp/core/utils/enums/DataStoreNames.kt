package com.example.turistaapp.core.utils.enums

sealed class DataStoreNames(val name: String){
    object Name : DataStoreNames("name")
    object IsDarkMode : DataStoreNames("isDarkMode")
    object IsMapTutorialComplete : DataStoreNames("isMapTutorialComplete")
    object IsShakeGameTutorialComplete : DataStoreNames("isShakeGameTutorialComplete")
}
