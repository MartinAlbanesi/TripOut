package com.example.turistaapp.create_trip.ui.screens // ktlint-disable package-name

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.turistaapp.create_trip.ui.screens.components.AddList
import com.example.turistaapp.create_trip.ui.screens.components.DateRangePickerInput
import com.example.turistaapp.create_trip.ui.screens.components.ExposedDropdownMenuBoxInput
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.create_trip.ui.screens.components.TextInputField
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import com.example.turistaapp.home.ui.components.TopAppBarScreen
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    address : String?,
    createTripViewModel: CreateTripViewModel = hiltViewModel(),
    onClickCreateTrip: () -> Unit,
) {

    LaunchedEffect(true){
        if(address != null){
            createTripViewModel.setDestination(address)
        }
    }

    // Nombre del Viaje
//    val tripName by createTripViewModel.name.observeAsState("")
    var tripName by rememberSaveable {
        mutableStateOf("")
    }

    // Origen
    val originAutocompleteQuery by createTripViewModel.originQuery.observeAsState("")
    val isOriginAutocompleteDropdownVisible by createTripViewModel.isOriginAutocompleteDropdownVisible.observeAsState(
        false,
    )
    val originPredictions by createTripViewModel.originPredictions.observeAsState(emptyList())

    // Destino
    val destinationAutocompleteQuery by createTripViewModel.destinationQuery.observeAsState("")
    val isDestinationAutocompleteDropdownVisible by createTripViewModel.isDestinationAutocompleteDropdownVisible.observeAsState(
        false,
    )
    val destinationPredictions by createTripViewModel.destinationPredictions.observeAsState(
        emptyList(),
    )

    // Fechas
    val startDate by createTripViewModel.startDate.observeAsState(createTripViewModel.calendar.timeInMillis)
    val endDate by createTripViewModel.endDate.observeAsState(createTripViewModel.calendar.timeInMillis)
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate,
        initialSelectedEndDateMillis = endDate,
    )
    val showDateRangePickerDialog by createTripViewModel.showDateRangePickerDialog.observeAsState(
        false,
    )

    // Acompañantes
    val members by createTripViewModel.members.observeAsState(emptyList())
    val memberName by createTripViewModel.memberName.observeAsState("")

    // Paradas
    val stops by createTripViewModel.stops.observeAsState(emptyList())
    val stopName by createTripViewModel.stopName.observeAsState("")

    // Transporte
    val transports by createTripViewModel.transports.observeAsState(
        emptyList(),
    )
    val isExpanded by createTripViewModel.isExpanded.observeAsState(false)
    val transport by createTripViewModel.transport.observeAsState("")
    // Descripción
    val description by createTripViewModel.description.observeAsState("")
    // Focus Requesters
    val originFocusRequester by createTripViewModel.originFocusRequester.observeAsState(
        FocusRequester(),
    )
    val destinationFocusRequester by createTripViewModel.destinationFocusRequester.observeAsState(
        FocusRequester(),
    )
    val descriptionFocusRequester by createTripViewModel.descriptionFocusRequester.observeAsState(
        FocusRequester(),
    )

//    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBarScreen(
                title = "Crear Viaje",
                isMarkerSelected = true
            ) {
                onClickCreateTrip()
            }
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = 8.dp,
                end = 8.dp,
                start = 8.dp
            ),
        ) {
            item {
                // Nombre del Viaje
                TextInputField(
                    label = "Nombre del Viaje",
                    textValue = tripName,
                    onValueChange = { tripName = it/*createTripViewModel.onNameChange(it)*/ },
                    focusRequester = originFocusRequester,
                    imeAction = ImeAction.Next,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AddHome, contentDescription = "Trip Title")
                    },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Origen
                PlaceAutocompleteField(
                    label = "Origen",
                    query = originAutocompleteQuery,
                    onQueryChange = { createTripViewModel.onOriginAutocompleteQueryValueChange(it) },
                    isDropdownVisible = isOriginAutocompleteDropdownVisible,
                    onDropdownVisibilityChange = {
                        createTripViewModel.onOriginAutocompleteDropdownVisibilityChange(it)
                    },
                    predictions = originPredictions,
                    onPredictionSelect = {
                        createTripViewModel.onOriginAutocompletePredictionSelect(
                            it,
                        )
                    },
                    focusRequester = originFocusRequester,
                    imeAction = ImeAction.Next,
                    onClearField = { createTripViewModel.onClearOriginField() },
                    onSelectedLocationChange = {
                        createTripViewModel.onSelectedOriginLocationChange(
                            it,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.TripOrigin,
                            contentDescription = "Origin",
                        )
                    },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Destino
                PlaceAutocompleteField(
                    label = "Destino",
                    query = destinationAutocompleteQuery,
                    onQueryChange = {
                        createTripViewModel.onDestinationAutocompleteQueryValueChange(
                            it,
                        )
                    },
                    isDropdownVisible = isDestinationAutocompleteDropdownVisible,
                    onDropdownVisibilityChange = {
                        createTripViewModel.onDestinationAutocompleteDropdownVisibilityChange(it)
                    },
                    predictions = destinationPredictions,
                    onPredictionSelect = {
                        createTripViewModel.onDestinationAutocompletePredictionSelect(
                            it,
                        )
                    },
                    focusRequester = destinationFocusRequester,
                    imeAction = ImeAction.Next,
                    onClearField = { createTripViewModel.onClearDestinationField() },
                    onSelectedLocationChange = {
                        createTripViewModel.onSelectedDestinationLocationChange(
                            it,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = "Destination",
                        )
                    },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Fechas
                DateRangePickerInput(
                    label = "Fechas",
                    startDate = startDate,
                    endDate = endDate,
                    dateRangePickerState = dateRangePickerState,
                    showDateRangePicker = showDateRangePickerDialog,
                    onDismiss = { createTripViewModel.onShowDateRangePickerDialogChange(it) },
                    onConfirm = {
                        dateRangePickerState.selectedStartDateMillis?.let { long ->
                            createTripViewModel.onStartDateChange(
                                long,
                            )
                        }
                        dateRangePickerState.selectedEndDateMillis?.let { long ->
                            createTripViewModel.onEndDateChange(
                                long,
                            )
                        }
                        createTripViewModel.onShowDateRangePickerDialogChange(it)
                    },
                ) { createTripViewModel.onShowDateRangePickerDialogChange(true) }

                Spacer(modifier = Modifier.size(4.dp))

                // Transporte
                ExposedDropdownMenuBoxInput(
                    label = "Transporte",
                    values = transports,
                    isExpanded = isExpanded,
                    transport = transport,
                    onExpanded = { createTripViewModel.onIsExpandedChange(it) },
                    onClickable = { createTripViewModel.onTransportChange(it) },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Acompañantes
                AddList(
                    label = "Acompañantes",
                    name = memberName,
                    values = members,
                    onValueNameChange = { createTripViewModel.onMemberNameChange(it) },
                    onAdd = { createTripViewModel.onAddMember(it) },
                    onRemove = { createTripViewModel.onRemoveMember(it) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Contacts,
                            contentDescription = "Member Name",
                        )
                    },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Paradas
                AddList(
                    label = "Puntos de Parada",
                    name = stopName,
                    values = stops,
                    onValueNameChange = { createTripViewModel.onStopNameChange(it) },
                    onAdd = { createTripViewModel.onAddStop(it) },
                    onRemove = { createTripViewModel.onRemoveStop(it) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AddLocation,
                            contentDescription = "Member Name",
                        )
                    },
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Descripción
                TextInputField(
                    label = "Descripción (Opcional)",
                    textValue = description,
                    onValueChange = { createTripViewModel.onDescriptionChange(it) },
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
                )

                Spacer(modifier = Modifier.size(8.dp))

                // Botón para guardar
                Button(
                    onClick = {
                        if (createTripViewModel.onCreateTripClick(tripName)) {
                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Viaje creado con éxito",
                                        actionLabel = "Cancelar",
                                        duration = SnackbarDuration.Indefinite,
                                    )
                            }
                        } else {
                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Error al crear el viaje",
                                        actionLabel = "Cancelar",
                                        duration = SnackbarDuration.Short,
                                    )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    // .align(Alignment.CenterHorizontally)
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
