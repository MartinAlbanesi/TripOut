package com.example.turistaapp.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.turistaapp.R
import com.example.turistaapp.core.ui.components.TopAppBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShakeGameScreen() {
    var x by remember {
        mutableFloatStateOf(0f)
    }
    var y by remember {
        mutableFloatStateOf(0f)
    }
    var z by remember {
        mutableFloatStateOf(0f)
    }

    var value by remember {
        mutableStateOf("")
    }

    var list by remember {
        mutableStateOf(listOf<String>())
    }

    var select by remember{
        mutableStateOf("")
    }

    val context = LocalContext.current

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    var isShake = false

    sensorManager.registerListener(object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.run {
                x = this.values[0]*2f;
                y = this.values[1]*2f;
                z = this.values[2]*2f;

                // Calcula la aceleración total
                var acceleration = Math.sqrt((x * x + y * y + z * z).toDouble());

                // Comprueba si la aceleración supera el umbral de agitación
                if (acceleration > 60f && !isShake && list.size > 2) {
                    Toast.makeText(context, "Shake event detected", Toast.LENGTH_SHORT).show()
                    select = list.random()
                    isShake = true
                }
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }

    }, sensor, SensorManager.SENSOR_DELAY_FASTEST)

    if(isShake){
        Dialog(onDismissRequest = { isShake = false }) {
            Text(text = select)
        }
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValue.calculateTopPadding(),
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentAlignment = Alignment.Center
            ){
                Column(Modifier.fillMaxWidth()) {

                    Text(
                        text = "¡Agite para una descubrir una ubicación!",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    AsyncImage(
                        model = R.drawable.ic_launcher_foreground,
                        modifier = Modifier
                            .offset(x = x.dp, y = y.dp)
                            .fillMaxWidth(),
                        contentDescription = null
                    )
                }
            }
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(text = "Ingrese una ubicación") },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.LightGray,
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        list += value
                        value = ""
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(list) {
                    Text(text = it)
                }
            }
        }
    }
}