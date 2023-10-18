package com.example.turistaapp.create_trip.ui.screens // ktlint-disable package-name

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.turistaapp.create_trip.ui.screens.components.AddList
import com.example.turistaapp.create_trip.ui.screens.components.DateRangePickerInput
import com.example.turistaapp.create_trip.ui.screens.components.ExposedDropdownMenuBoxInput
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.create_trip.ui.screens.components.TextInputField
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(
    innerPadding: PaddingValues,
    createTripViewModel: CreateTripViewModel = hiltViewModel(),
) {
    // Nombre del Viaje
    val tripName by createTripViewModel.name.observeAsState("")

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
    val isMemberDialogOpen by createTripViewModel.isMemberDialogOpen.observeAsState(false)

    // Paradas
    val stops by createTripViewModel.stops.observeAsState(emptyList())
    val stopName by createTripViewModel.stopName.observeAsState("")
    val isStopDialogOpen by createTripViewModel.isStopDialogOpen.observeAsState(false)

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

    val context = LocalContext.current

    /*
    TripFormContent(
        innerPadding,
        tripName = tripName,
        onNameChange = { createTripViewModel.onNameChange(it) },
        startDate = startDate,
        endDate = endDate,
        dateRangePickerState = dateRangePickerState,
        showDateRangePickerDialog = showDateRangePickerDialog,
        onDismissDateRangePickerDialog = { createTripViewModel.onShowDateRangePickerDialogChange(it) },
        onConfirmDateRangePickerDialog = { it ->
            dateRangePickerState.selectedStartDateMillis?.let {
                createTripViewModel.onStartDateChange(
                    it,
                )
            }
            dateRangePickerState.selectedEndDateMillis?.let { createTripViewModel.onEndDateChange(it) }
            createTripViewModel.onShowDateRangePickerDialogChange(it)
        },
        members = members,
        memberName = memberName,
        isMemberDialogOpen = isMemberDialogOpen,
        onMemberNameChange = { createTripViewModel.onMemberNameChange(it) },
        onMemberDialogOpenChange = {
            createTripViewModel.onMemberDialogOpenChange(it)
            createTripViewModel.resetMemberNameValue()
        },
        onAddMember = { createTripViewModel.onAddMember(it) },
        onRemoveMember = { createTripViewModel.onRemoveMember(it) },
        stops = stops,
        stopName = stopName,
        isStopDialogOpen = isStopDialogOpen,
        onStopNameChange = { createTripViewModel.onStopNameChange(it) },
        onStopDialogOpenChange = {
            createTripViewModel.onStopDialogOpenChange(it)
            createTripViewModel.resetStopNameValue()
        },
        onAddStop = { createTripViewModel.onAddStop(it) },
        onRemoveStop = { createTripViewModel.onRemoveStop(it) },
        transports = transports,
        isExpanded = isExpanded,
        onIsExpandedChange = { createTripViewModel.onIsExpandedChange(it) },
        transport = transport,
        onTransportChange = { createTripViewModel.onTransportChange(it) },
        description = description,
        onDescriptionChange = { createTripViewModel.onDescriptionChange(it) },
        onCreateTripClick = {
            if (createTripViewModel.onCreateTripClick()) {
                Toast.makeText(context, "Viaje creado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al crear el viaje", Toast.LENGTH_SHORT).show()
            }
        },
        originFocusRequester = originFocusRequester,
        destinationFocusRequester = destinationFocusRequester,
        descriptionFocusRequester = descriptionFocusRequester,
        originAutocompleteQuery = originAutocompleteQuery,
        onOriginAutocompleteQueryValueChange = {
            createTripViewModel.onOriginAutocompleteQueryValueChange(it)
        },
        isOriginAutocompleteDropdownVisible = isOriginAutocompleteDropdownVisible,
        onOriginAutocompleteDropdownVisibilityChange = {
            createTripViewModel.onOriginAutocompleteDropdownVisibilityChange(it)
        },
        originAutocompletePredictions = originPredictions,
        onOriginAutocompletePredictionSelect = {
            createTripViewModel.onOriginAutocompletePredictionSelect(it)
        },
        onClearOriginField = { createTripViewModel.onClearOriginField() },
        onSelectedOriginLocationChange = { createTripViewModel.onSelectedOriginLocationChange(it) },
        destinationAutocompleteQuery = destinationAutocompleteQuery,
        onDestinationAutocompleteQueryValueChange = {
            createTripViewModel.onDestinationAutocompleteQueryValueChange(it)
        },
        isDestinationAutocompleteDropdownVisible = isDestinationAutocompleteDropdownVisible,
        onDestinationAutocompleteDropdownVisibilityChange = {
            createTripViewModel.onDestinationAutocompleteDropdownVisibilityChange(it)
        },
        destinationAutocompletePredictions = destinationPredictions,
        onDestinationAutocompletePredictionSelect = {
            createTripViewModel.onDestinationAutocompletePredictionSelect(it)
        },
        onClearDestinationField = { createTripViewModel.onClearDestinationField() },
        onSelectedDestinationLocationChange = {
            createTripViewModel.onSelectedDestinationLocationChange(
                it,
            )
        },
    )
     */
    LazyColumn(
        modifier = Modifier.padding(
            vertical = innerPadding.calculateTopPadding(),
            horizontal = 8.dp,
        ),
    ) {
        item {
            // Nombre del Viaje
            TextInputField(
                label = "Nombre del Viaje",
                textValue = tripName,
                onValueChange = { createTripViewModel.onNameChange(it) },
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next,
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
                onPredictionSelect = { createTripViewModel.onOriginAutocompletePredictionSelect(it) },
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next,
                onClearField = { createTripViewModel.onClearOriginField() },
                onSelectedLocationChange = { createTripViewModel.onSelectedOriginLocationChange(it) },
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Destino
            PlaceAutocompleteField(
                label = "Destino",
                query = destinationAutocompleteQuery,
                onQueryChange = { createTripViewModel.onDestinationAutocompleteQueryValueChange(it) },
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
                    dateRangePickerState.selectedStartDateMillis?.let {
                        createTripViewModel.onStartDateChange(
                            it,
                        )
                    }
                    dateRangePickerState.selectedEndDateMillis?.let {
                        createTripViewModel.onEndDateChange(
                            it,
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

            // Descripción
            TextInputField(
                label = "Descripción (Opcional)",
                textValue = description,
                onValueChange = { createTripViewModel.onDescriptionChange(it) },
                focusRequester = descriptionFocusRequester,
                imeAction = ImeAction.Done,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Acompañantes
            AddList(
                label = "Acompañantes",
                name = memberName,
                values = members,
                isDialogOpen = isMemberDialogOpen,
                onValueNameChange = { createTripViewModel.onMemberNameChange(it) },
                onDialogOpenChange = {
                    createTripViewModel.onMemberDialogOpenChange(it)
                    createTripViewModel.resetMemberNameValue()
                },
                onAdd = { createTripViewModel.onAddMember(it) },
                onRemove = { createTripViewModel.onRemoveMember(it) },
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Paradas
            AddList(
                label = "Puntos de Parada",
                name = stopName,
                values = stops,
                isDialogOpen = isStopDialogOpen,
                onValueNameChange = { createTripViewModel.onStopNameChange(it) },
                onDialogOpenChange = {
                    createTripViewModel.onStopDialogOpenChange(it)
                    createTripViewModel.resetStopNameValue()
                },
                onAdd = { createTripViewModel.onAddStop(it) },
                onRemove = { createTripViewModel.onRemoveStop(it) },
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Botón para guardar
            Button(
                onClick = {
                    if (createTripViewModel.onCreateTripClick()) {
                        Toast.makeText(context, "Viaje creado con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Error al crear el viaje", Toast.LENGTH_SHORT)
                            .show()
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

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripFormContent(
    innerPadding: PaddingValues,
    tripName: String,
    onNameChange: (String) -> Unit,
    startDate: Long,
    endDate: Long,
    dateRangePickerState: DateRangePickerState,
    showDateRangePickerDialog: Boolean,
    onDismissDateRangePickerDialog: (Boolean) -> Unit,
    onConfirmDateRangePickerDialog: (Boolean) -> Unit,
    members: List<String>,
    memberName: String,
    isMemberDialogOpen: Boolean,
    onMemberNameChange: (String) -> Unit,
    onMemberDialogOpenChange: (Boolean) -> Unit,
    onAddMember: (String) -> Unit,
    onRemoveMember: (String) -> Unit,
    stops: List<String>,
    stopName: String,
    isStopDialogOpen: Boolean,
    onStopNameChange: (String) -> Unit,
    onStopDialogOpenChange: (Boolean) -> Unit,
    onAddStop: (String) -> Unit,
    onRemoveStop: (String) -> Unit,
    transports: List<String>,
    isExpanded: Boolean,
    onIsExpandedChange: (Boolean) -> Unit,
    transport: String,
    onTransportChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onCreateTripClick: () -> Unit,
    originFocusRequester: FocusRequester,
    destinationFocusRequester: FocusRequester,
    descriptionFocusRequester: FocusRequester,
    originAutocompleteQuery: String,
    onOriginAutocompleteQueryValueChange: (String) -> Unit,
    isOriginAutocompleteDropdownVisible: Boolean,
    onOriginAutocompleteDropdownVisibilityChange: (Boolean) -> Unit,
    originAutocompletePredictions: List<PlaceAutocompletePredictionModel>,
    onOriginAutocompletePredictionSelect: (PlaceAutocompletePredictionModel) -> Unit,
    onClearOriginField: () -> Unit,
    onSelectedOriginLocationChange: (PlaceAutocompletePredictionModel) -> Unit,
    destinationAutocompleteQuery: String,
    onDestinationAutocompleteQueryValueChange: (String) -> Unit,
    isDestinationAutocompleteDropdownVisible: Boolean,
    onDestinationAutocompleteDropdownVisibilityChange: (Boolean) -> Unit,
    destinationAutocompletePredictions: List<PlaceAutocompletePredictionModel>,
    onDestinationAutocompletePredictionSelect: (PlaceAutocompletePredictionModel) -> Unit,
    onClearDestinationField: () -> Unit,
    onSelectedDestinationLocationChange: (PlaceAutocompletePredictionModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(8.dp),
    ) {
        item {
            // Nombre del Viaje
            TextInputField(
                label = "Nombre del Viaje",
                textValue = tripName,
                onValueChange = onNameChange,
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Origen y Destino
            // Buscador de lugares
            PlaceAutocompleteField(
                label = "Origen",
                query = originAutocompleteQuery,
                onQueryChange = onOriginAutocompleteQueryValueChange,
                isDropdownVisible = isOriginAutocompleteDropdownVisible,
                onDropdownVisibilityChange = onOriginAutocompleteDropdownVisibilityChange,
                predictions = originAutocompletePredictions,
                onPredictionSelect = onOriginAutocompletePredictionSelect,
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next,
                onClearField = onClearOriginField,
                onSelectedLocationChange = onSelectedOriginLocationChange,
            )

            Spacer(modifier = Modifier.size(4.dp))

            PlaceAutocompleteField(
                label = "Destino",
                query = destinationAutocompleteQuery,
                onQueryChange = onDestinationAutocompleteQueryValueChange,
                isDropdownVisible = isDestinationAutocompleteDropdownVisible,
                onDropdownVisibilityChange = onDestinationAutocompleteDropdownVisibilityChange,
                predictions = destinationAutocompletePredictions,
                onPredictionSelect = onDestinationAutocompletePredictionSelect,
                focusRequester = destinationFocusRequester,
                imeAction = ImeAction.Next,
                onClearField = onClearDestinationField,
                onSelectedLocationChange = onSelectedDestinationLocationChange,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Fechas
            DateRangePickerInput(
                label = "Fechas",
                startDate = startDate,
                endDate = endDate,
                dateRangePickerState = dateRangePickerState,
                showDateRangePicker = showDateRangePickerDialog,
                onDismiss = { onDismissDateRangePickerDialog(it) },
                onConfirm = { onConfirmDateRangePickerDialog(it) },
            ) { onDismissDateRangePickerDialog(true) }

            Spacer(modifier = Modifier.size(4.dp))

            // Transporte
            ExposedDropdownMenuBoxInput(
                label = "Transporte",
                values = transports,
                isExpanded = isExpanded,
                transport = transport,
                onExpanded = onIsExpandedChange,
                onClickable = onTransportChange,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Descripción Opcional
            TextInputField(
                label = "Descripción (Opcional)",
                textValue = description,
                onValueChange = onDescriptionChange,
                focusRequester = descriptionFocusRequester,
                imeAction = ImeAction.Done,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Integrantes
            AddList(
                label = "Acompañantes",
                name = memberName,
                values = members,
                isDialogOpen = isMemberDialogOpen,
                onValueNameChange = { onMemberNameChange(it) },
                onDialogOpenChange = { onMemberDialogOpenChange(it) },
                onAdd = onAddMember,
                onRemove = onRemoveMember,
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Paradas
            AddList(
                label = "Puntos de Parada",
                name = stopName,
                values = stops,
                isDialogOpen = isStopDialogOpen,
                onValueNameChange = { onStopNameChange(it) },
                onDialogOpenChange = { onStopDialogOpenChange(it) },
                onAdd = onAddStop,
                onRemove = onRemoveStop,
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Botón para guardar
            Button(
                onClick = onCreateTripClick,
                modifier = Modifier
                    .fillMaxWidth(),
                // .align(Alignment.CenterHorizontally)
            ) {
                Text("Guardar")
            }
        }
    }
}
 */
