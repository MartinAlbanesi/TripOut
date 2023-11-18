package com.example.turistaapp.core.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.turistaapp.core.utils.enums.Routes
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.home.ui.HomeViewModel
import com.example.turistaapp.map.ui.MapScreen
import com.example.turistaapp.map.ui.viewmodel.MapViewModel
import com.example.turistaapp.my_trips.ui.viewmodels.MyTripsViewModel
import com.example.turistaapp.qr_code.domain.models.DataQRModel
import com.example.turistaapp.qr_code.ui.QRViewModel
import com.example.turistaapp.setting.ui.SettingsScreen
import com.google.gson.Gson
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun MainScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    myTripsViewModel: MyTripsViewModel = hiltViewModel(),
    createTripViewModel: CreateTripViewModel = hiltViewModel(),
    qrViewModel: QRViewModel = hiltViewModel(),
    navController: NavHostController,
    onClickChangeTheme: () -> Unit,
) {
    // Home
    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val locationSelect by homeViewModel.locationSelect.collectAsStateWithLifecycle()

    // Map
    val destinationLocations by mapViewModel.destinationLocations.collectAsStateWithLifecycle()

    val directionSelect by mapViewModel.polyLinesPoints.collectAsStateWithLifecycle()

    val markerSelect by mapViewModel.markerSelect.collectAsStateWithLifecycle()

    val lastLocation by mapViewModel.lastLocation.collectAsStateWithLifecycle()

    val routeModel by mapViewModel.tripSelected.collectAsStateWithLifecycle()

    val isTutorialComplete by mapViewModel.isMapTutorialComplete.collectAsStateWithLifecycle(false)

    // Trips
    val myTrips by myTripsViewModel.trips.collectAsStateWithLifecycle()

    val isQRDialogOpen by qrViewModel.isQRDialogOpen.collectAsStateWithLifecycle()

    val dataQRSelected by qrViewModel.dataQRSelected.collectAsStateWithLifecycle()

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            val dataQR = Gson().fromJson(result.contents, DataQRModel::class.java)
            if (dataQR != null) {
                createTripViewModel.createTripFromQR(dataQR)
                Toast.makeText(
                    navController.context,
                    "Viaje ${dataQR.name} creado con exito",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                Toast.makeText(
                    navController.context,
                    "Error al leer el codigo QR",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        },
    )

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        Routes.Home,
        Routes.Map,
        Routes.Settings,
    )
    Column {
        TabRow(
            selectedTabIndex = state,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    icon = { Icon(title.icon!!, contentDescription = null)},
//                    text = { Text(title.route) },
                    selected = state == index,
                    onClick = { state = index },
                )
            }
        }
        when (titles[state].route) {
            Routes.Home.route -> {
                HomeScreen(
                    nearbyLocations = nearbyLocations,
                    nearbyLocationSelect = nearbyLocationSelect,
                    myTrips = myTrips,
                    locationSelect = locationSelect,
                    isQRDialogOpen = isQRDialogOpen,
                    dataQRSelected = dataQRSelected,
                    onIsQRDialogOpenChange = { qrViewModel.onIsQRDialogOpenChange(it) },
                    onDataQRSelectedChange = { qrViewModel.onDataQRSelectedChange(it) },
                    onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it)) },
                    onCardSelection = { homeViewModel.setNearbyLocationSelect(it) },
                    onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
                    onClickShakeGame = { navController.navigate(Routes.ShakeGame.route) },
                    onTripClick = {
                        state = 1
                        mapViewModel.getTripById(it)
                    },
                    onQRButtonClick = {
                        val options = ScanOptions()
                        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                        options.setPrompt("Scan a barcode")
                        options.setBarcodeImageEnabled(true)
                        options.setOrientationLocked(true)
                        options.setBeepEnabled(false)
                        scanLauncher.launch(options)
                    },
                    onDeleteTripButtonClick = {
                        myTripsViewModel.deleteTrip(it)
                        Toast.makeText(
                            navController.context,
                            "Viaje eliminado con exito",
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                )
            }

            Routes.Map.route -> {
                MapScreen(
                    locations = destinationLocations,
                    directionSelect = directionSelect,
                    markerSelect = markerSelect,
                    lastLocation = lastLocation,
                    routeModel = routeModel,
                    isTutorialComplete = isTutorialComplete,
                    isQRDialogOpen = isQRDialogOpen,
                    dataQRSelected = dataQRSelected,
                    onMarkerSelectChange = { mapViewModel.onMarkerSelectChange(it) },
                    onDismissQRDialog = { qrViewModel.onIsQRDialogOpenChange(false) },
                    onIsQRDialogOpenChange = { qrViewModel.onIsQRDialogOpenChange(it) },
                    onDataQRSelectedChange = { qrViewModel.onDataQRSelectedChange(it) },
                    onClickArrowBack = { mapViewModel.getFlowLocationFromDB() },
                    onMarkerSelected = { mapViewModel.getTripById(it) },
                    onClickFinishTutorial = { mapViewModel.setMapTutorial() },
                    onDeleteTripButtonClick = {
                        myTripsViewModel.deleteTrip(it)
                        Toast.makeText(
                            navController.context,
                            "Viaje eliminado con exito",
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                )
            }

            Routes.Settings.route -> {
                SettingsScreen() {
                    onClickChangeTheme()
                }
            }
        }
    }
}
