package com.example.turistaapp.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.turistaapp.core.ui.components.TopAppBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun ShakeGameScreen() {

    var value by remember{
        mutableStateOf("")
    }

    var list by remember{
        mutableStateOf(listOf<String>())
    }

    Scaffold(
        topBar = {
            TopAppBarScreen(
                title = "Shake'n Descover",
                onClickNavigationBack = {
                    //TODO: Volver a home
                }
            )
        }
    ) { paddingValue ->
        Column(Modifier.fillMaxSize().padding(paddingValue.calculateTopPadding())) {
            Text(text = "¡Agite para una descubrir una ubicación!")
            AsyncImage(model = "", contentDescription = null)
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(text = "Ingrese una ubicación para agregar a la lista") },
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        list += value
//                        value = ""
//                    }
//                )
            )
            LazyColumn(){
                items(list){
                    Text(text = it)
                }
            }
        }
    }
}