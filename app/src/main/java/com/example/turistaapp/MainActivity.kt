package com.example.turistaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.turistaapp.main.ui.MainScreen
import com.example.turistaapp.setting.ui.SettingViewModel
import com.example.turistaapp.setting.ui.SettingsScreen
import com.example.turistaapp.setting.ui.TripDetail
import com.example.turistaapp.ui.theme.TuristaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val settingViewModel : SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by settingViewModel.darkTheme.observeAsState(false)

            TuristaAppTheme(darkTheme = isDarkTheme){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
 //                   MainScreen()
                /*    SettingsScreen(darkTheme = isDarkTheme) {
                        settingViewModel.changeTheme()
                    }*/
                    TripDetail()
                }
            }
        }
    }
}

