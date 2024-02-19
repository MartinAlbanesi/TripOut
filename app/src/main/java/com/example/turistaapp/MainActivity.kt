package com.example.turistaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.TuristaAppTheme
import com.example.turistaapp.core.ui.NavHostScreen
import com.example.turistaapp.map.ui.SelectLocationMap
import com.example.turistaapp.setting.ui.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                settingViewModel.isLoading.value
            }
        }
        setContent {
            val isDarkTheme by settingViewModel.darkTheme.collectAsStateWithLifecycle(false)

            TuristaAppTheme(darkTheme = isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
//                    NavHostScreen(onClickChangeTheme = { settingViewModel.changeTheme() })
                    SelectLocationMap()
                }
            }
        }
    }
}
