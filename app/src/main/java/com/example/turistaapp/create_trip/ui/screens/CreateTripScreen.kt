package com.example.turistaapp.create_trip.ui.screens // ktlint-disable package-name

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.turistaapp.R
import com.example.turistaapp.core.ui.components.TopAppBarScreen
import com.example.turistaapp.core.utils.Transport
import com.example.turistaapp.core.utils.Transports
import com.example.turistaapp.create_trip.ui.screens.components.AddList
import com.example.turistaapp.create_trip.ui.screens.components.DateRangePickerInput
import com.example.turistaapp.create_trip.ui.screens.components.ExposedDropdownMenuBoxInput
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.create_trip.ui.screens.components.TextInputField
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import com.example.turistaapp.create_trip.utils.dateFormat
import com.example.turistaapp.create_trip.utils.getCurrentDate
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    address: String?,
    createTripViewModel: CreateTripViewModel = hiltViewModel(),
    navigateToSelectLocationFromMap: (String) -> Unit,
    onClickCreateTrip: () -> Unit,
) {


    // Nombre del Viaje
    var tripName by rememberSaveable {
        mutableStateOf("")
    }

    // Origen
    var originAutocompleteQuery by rememberSaveable {
        mutableStateOf("")
    }
    var isOriginAutocompleteDropdownVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val originPredictions by createTripViewModel.originPredictions.observeAsState(emptyList())

    // Destino
    var destinationAutocompleteQuery by rememberSaveable {
        mutableStateOf(address ?: "")
    }
    var isDestinationAutocompleteDropdownVisible by rememberSaveable {
        mutableStateOf(true)
    }
    val destinationPredictions by createTripViewModel.destinationPredictions.observeAsState(
        emptyList(),
    )

    LaunchedEffect(true) {
        if (!address.isNullOrBlank()) {
            createTripViewModel.setDestination(address)


            isDestinationAutocompleteDropdownVisible = false
            delay(1000)
            createTripViewModel.onSelectedDestinationLocationChange(destinationPredictions[0])
        }
    }

    var startDate by rememberSaveable {
        mutableStateOf(getCurrentDate())
    }

    var endDate by rememberSaveable {
        mutableStateOf(getCurrentDate())
    }


    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = LocalDateTime.now()
            .toEpochSecond(java.time.ZoneOffset.UTC) * 1000,
        initialSelectedEndDateMillis = Date().time
    )

    var showDateRangePickerDialog by remember {
        mutableStateOf(false)
    }

    // Acompañantes
    val members by createTripViewModel.members.observeAsState(emptyList())

    var memberName by rememberSaveable {
        mutableStateOf("")
    }

    val transports = listOf(
        Transport(Transports.Driving.type, stringResource(R.string.driving)),
        Transport(Transports.Bicycling.type, stringResource(R.string.bicycling)),
        Transport(Transports.Walking.type, stringResource(R.string.walking)),
    )

    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var transport by remember {
        mutableStateOf(transports[0])
    }

    // Descripción
    var description by rememberSaveable {
        mutableStateOf("")
    }

    // Focus Requesters
    val originFocusRequester = remember { FocusRequester() }
    val destinationFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    // Validaciones
    var isTripNameValid by rememberSaveable {
        mutableStateOf(true)
    }

    var isOriginValid by rememberSaveable {
        mutableStateOf(true)
    }

    var isDestinationValid by rememberSaveable {
        mutableStateOf(true)
    }

    var isMemberNameValid by rememberSaveable {
        mutableStateOf(true)
    }

    var isCreateTripSuccessful by remember { mutableStateOf(false) }

    val lottie = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success))

    if (isCreateTripSuccessful) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center,
        ) {
            Dialog(onDismissRequest = {}) {
                LottieAnimation(
                    composition = lottie.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                )

                LaunchedEffect(lottie.isComplete) {
                    delay(1600)
                    onClickCreateTrip()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarScreen(
                title = stringResource(R.string.create_trip),
                isMarkerSelected = true,
                onClickNavigationBack = { onClickCreateTrip() },
            )
        },
        bottomBar = {
            // Botón para guardar
            Button(
                onClick = {
                    isTripNameValid = createTripViewModel.validateTripName(tripName)
                    isOriginValid = createTripViewModel.validateTripOrigin()
                    isDestinationValid = createTripViewModel.validateTripDestination()

                    if (isTripNameValid && isOriginValid && isDestinationValid) {
                        createTripViewModel.onCreateTripClick(
                            tripName,
                            description,
                            transport.type,
                            startDate,
                            endDate,
                        )

                        isCreateTripSuccessful = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .size(45.dp),
            ) {
                Text(stringResource(R.string.save_trip))
            }
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                end = 8.dp,
                start = 8.dp,
            ),
        ) {
            item {
                // Nombre del Viaje
                TextInputField(
                    label = stringResource(R.string.trip_name),
                    textValue = tripName,
                    onValueChange = {
                        tripName = it
                        isTripNameValid = true
                    },
                    focusRequester = originFocusRequester,
                    imeAction = ImeAction.Next,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AddHome, contentDescription = "Trip Title")
                    },
                    onClearField = { tripName = "" },
                    isError = !isTripNameValid,
                )

                Spacer(modifier = Modifier.size(8.dp))
            }

            item {
                // Origen
                PlaceAutocompleteField(
                    label = "${stringResource(R.string.origen)} *",
                    query = originAutocompleteQuery,
                    onQueryChange = {
                        originAutocompleteQuery = it
                        isOriginValid = true
                        createTripViewModel.searchOriginPlaces(originAutocompleteQuery)
                    },
                    isDropdownVisible = isOriginAutocompleteDropdownVisible,
                    onDropdownVisibilityChange = {
                        isOriginAutocompleteDropdownVisible = it
                    },
                    predictions = originPredictions,
                    focusRequester = originFocusRequester,
                    imeAction = ImeAction.Next,
                    onClearField = {
                        originAutocompleteQuery = ""
                        createTripViewModel.clearSelectedOriginLocation()
                    },
                    leadingIcon = Icons.Default.TripOrigin,
                    onItemClick = {
                        originAutocompleteQuery = it.description ?: ""
                        isOriginAutocompleteDropdownVisible = false
                        createTripViewModel.onSelectedOriginLocationChange(it)
                    },
                    isError = !isOriginValid,
                    onClickSelectedLocation = { navigateToSelectLocationFromMap("geocode") },
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                // Destino
                PlaceAutocompleteField(
                    label = stringResource(R.string.destination),
                    query = destinationAutocompleteQuery,
                    onQueryChange = {
                        destinationAutocompleteQuery = it
                        isDestinationValid = true
                        createTripViewModel.searchDestinationPlaces(destinationAutocompleteQuery)
                    },
                    isDropdownVisible = isDestinationAutocompleteDropdownVisible,
                    onDropdownVisibilityChange = {
                        isDestinationAutocompleteDropdownVisible = it
                    },
                    predictions = destinationPredictions,
                    focusRequester = destinationFocusRequester,
                    imeAction = ImeAction.Next,
                    onClearField = {
                        destinationAutocompleteQuery = ""
                        createTripViewModel.clearSelectedDestinationLocation()
                    },
                    leadingIcon = Icons.Default.Flag,
                    onItemClick = {
                        destinationAutocompleteQuery = it.description ?: ""
                        isDestinationAutocompleteDropdownVisible = false
                        createTripViewModel.onSelectedDestinationLocationChange(it)
                    },
                    isError = !isDestinationValid,
                    onClickSelectedLocation = { navigateToSelectLocationFromMap("establishment") },
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                // Fechas
                DateRangePickerInput(
                    label = stringResource(R.string.dates),
                    startDate = startDate,
                    endDate = endDate,
                    dateRangePickerState = dateRangePickerState,
                    showDateRangePicker = showDateRangePickerDialog,
                    onDismiss = {
                        showDateRangePickerDialog = it
                    },
                    onConfirm = {
                        dateRangePickerState.selectedStartDateMillis?.let { long ->
                            startDate = dateFormat(long)
                        }

                        dateRangePickerState.selectedEndDateMillis?.let { long ->
                            endDate = dateFormat(long)
                        }
                        showDateRangePickerDialog = it
                    },
                    onClickable = {
                        showDateRangePickerDialog = true
                    },
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                // Transporte
                ExposedDropdownMenuBoxInput(
                    label = stringResource(R.string.transport),
                    values = transports,
                    isExpanded = isExpanded,
                    transport = transport,
                    onExpanded = {
                        isExpanded = it
                    },
                    onClickable = { transport = it },
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                // Acompañantes
                AddList(
                    label = stringResource(R.string.member),
                    name = memberName,
                    values = members,
                    onValueNameChange = {
                        memberName = it
                        isMemberNameValid = true
                    },
                    onAdd = {
                        if (memberName.isBlank() || memberName.length < 3 || memberName.length > 20 || members.contains(
                                memberName,
                            )
                        ) {
                            isMemberNameValid = false
                        } else {
                            createTripViewModel.onAddMember(it)
                            memberName = ""
                        }
                    },
                    onRemove = { createTripViewModel.onRemoveMember(it) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Contacts,
                            contentDescription = "Member Name",
                        )
                    },
                    isError = !isMemberNameValid,
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                // Descripción
                TextInputField(
                    label = stringResource(R.string.description),
                    textValue = description,
                    onValueChange = {
                        description = it
                    },
                    focusRequester = descriptionFocusRequester,
                    imeAction = ImeAction.Done,
                    singleLine = false,
                    maxLines = 3,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Trip Title",
                        )
                    },
                    onClearField = { description = "" },
                )
            }
        }
    }
}
