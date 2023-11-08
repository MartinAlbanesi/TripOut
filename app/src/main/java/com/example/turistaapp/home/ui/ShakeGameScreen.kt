package com.example.turistaapp.home.ui

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.turistaapp.R
import com.example.turistaapp.core.ui.components.TopAppBarScreen
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.map.ui.components.TripDialog
import kotlin.math.sqrt


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShakeGameScreen(
    shakeViewModel: ShakeViewModel = hiltViewModel(),
    onCreateTripDialog: (String) -> Unit,
) {

    val lottie = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.world))

    val originPredictions by shakeViewModel.originPredictions.observeAsState(emptyList())

    val selectedLocations by shakeViewModel.selectedLocations.collectAsStateWithLifecycle()

    val focusRequest = remember { FocusRequester() }

    var x by remember { mutableFloatStateOf(0f) }
    var y by remember { mutableFloatStateOf(0f) }
    var z by remember { mutableFloatStateOf(0f) }

    var value by remember { mutableStateOf("") }

    var isMenuVisible by remember { mutableStateOf(false) }

    val sensorManager = shakeViewModel.getSensorManager()
    val sensor = shakeViewModel.getSensorAcceleration()
    var isShake by remember { mutableStateOf(false) }

    var acceleration by remember { mutableDoubleStateOf(0.0) }

    var locationSelected: LocationModel? by remember { mutableStateOf(null) }

    sensorManager.registerListener(object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.run {
                x = this.values[0] * 2f;
                y = this.values[1] * 2f;
                z = this.values[2] * 2f;

                // Calcula la aceleración total
                acceleration = sqrt((x * x + y * y + z * z).toDouble());

                // Comprueba si la aceleración supera el umbral de agitación
                if (acceleration > 60f && !isShake && selectedLocations.size >= 2) {
//                    Toast.makeText(LocalContext.current, "Shake detected", Toast.LENGTH_SHORT).show()
                    locationSelected = selectedLocations.random()
                    isShake = true
                }
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }, sensor, SensorManager.SENSOR_DELAY_FASTEST)





    if (isShake) {
        TripDialog(
            nearbyLocationSelect = locationSelected!!,
            onDismiss = { isShake = false },
            onConfirm = {
                isShake = false
                onCreateTripDialog(it)
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBarScreen(
                title = "Shake'n Descover",
                isMarkerSelected = true,
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
            ) {
                Column(Modifier.fillMaxWidth()) {

                    Text(
                        text = "¡Agite para una descubrir una ubicación!",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    LottieAnimation(
                        composition = lottie.value,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .offset(x = x.dp, y = y.dp)
                            .fillMaxWidth(),
                    )
//                    AsyncImage(
//                        model = R.drawable.ic_launcher_foreground,
//                        modifier = Modifier
//                            .offset(x = x.dp, y = y.dp)
//                            .fillMaxWidth(),
//                        contentDescription = null
//                    )
                }
            }
            PlaceAutocompleteField(
                label = "Ingrese una ubicación",
                query = value,
                onQueryChange = {
                    value = it
                    shakeViewModel.searchOriginPlaces(value)
                },
                isDropdownVisible = isMenuVisible,
                onDropdownVisibilityChange = {
                    isMenuVisible = it
                },
                predictions = originPredictions,
                focusRequester = focusRequest,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                onClearField = {
                    value = ""
                },
                onItemClick = {
                    value = it.description ?: ""
//                    createTripViewModel.onSelectedOriginLocationChange(it)
                    shakeViewModel.onClickSelectedLocation(it.placeId)
                    value = ""
                    isMenuVisible = false
                    focusRequest.freeFocus()
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Map, contentDescription = null)
                }
            )
//            OutlinedTextField(
//                value = value,
//                onValueChange = { value = it },
//                label = { Text(text = "Ingrese una ubicación") },
//                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = MaterialTheme.colorScheme.primary,
//                    unfocusedBorderColor = Color.LightGray,
//                ),
//                keyboardOptions = KeyboardOptions(
//                    imeAction = androidx.compose.ui.text.input.ImeAction.Done
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        list += value
//                        value = ""
//                    }
//                ),
//                modifier = Modifier.fillMaxWidth()
//            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(selectedLocations) {
                    Text(text = "${it.name} - ${it.address}")
                }
            }
        }
    }
}