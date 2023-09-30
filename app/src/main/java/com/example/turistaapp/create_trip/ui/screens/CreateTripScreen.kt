package com.example.turistaapp.create_trip.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.ui.screens.components.AddList
import com.example.turistaapp.create_trip.ui.screens.components.DateRangePickerInput
import com.example.turistaapp.create_trip.ui.screens.components.ExposedDropdownMenuBoxInput
import com.example.turistaapp.create_trip.ui.screens.components.TextInputField
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(innerPadding: PaddingValues, createTripViewModel: CreateTripViewModel) {

    //Nombre del Viaje
    val tripName by createTripViewModel.name.observeAsState("")

    //Origen y Destino
    val origin by createTripViewModel.origin.observeAsState("")
    val destination by createTripViewModel.destination.observeAsState("")

    //Fechas
    val startDate by createTripViewModel.startDate.observeAsState(createTripViewModel.calendar.timeInMillis)
    val endDate by createTripViewModel.endDate.observeAsState(createTripViewModel.calendar.timeInMillis)
    //FALTA IMPLEMENTAR EL DATE RANGE PICKER STATE EN EL VIEWMODEL
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate,
        initialSelectedEndDateMillis = endDate
    )
    val showDateRangePickerDialog by createTripViewModel.showDateRangePickerDialog.observeAsState(
        false
    )

    //Acompañantes
    val members by createTripViewModel.members.observeAsState(emptyList())
    val valueName by createTripViewModel.valueName.observeAsState("")
    val isDialogOpen by createTripViewModel.isDialogOpen.observeAsState(false)



    //Paradas
    val stops by createTripViewModel.stops.observeAsState(emptyList())

    //Transporte
    val transports by createTripViewModel.transports.observeAsState(
        emptyList()
    )
    val isExpanded by createTripViewModel.isExpanded.observeAsState(false)
    val transport by createTripViewModel.transport.observeAsState("")
    //Descripción
    val description by createTripViewModel.description.observeAsState("")
    //Focus Requesters
    val originFocusRequester by createTripViewModel.originFocusRequester.observeAsState(
        FocusRequester()
    )
    val destinationFocusRequester by createTripViewModel.destinationFocusRequester.observeAsState(
        FocusRequester()
    )
    val membersFocusRequester by createTripViewModel.membersFocusRequester.observeAsState(
        FocusRequester()
    )
    val descriptionFocusRequester by createTripViewModel.descriptionFocusRequester.observeAsState(
        FocusRequester()
    )

    TripFormContent(
        innerPadding,
        tripName = tripName,
        onNameChange = { createTripViewModel.onNameChange(it) },
        origin = origin,
        onOriginChange = { createTripViewModel.onOriginChange(it) },
        destination = destination,
        onDestinationChange = { createTripViewModel.onDestinationChange(it) },
        startDate = startDate,
        endDate = endDate,
        dateRangePickerState = dateRangePickerState,
        showDateRangePickerDialog = showDateRangePickerDialog,
        onShowDateRangePickerDialog = { createTripViewModel.onShowDateRangePickerDialogChange(it) },
        onConfirmDateRangePickerDialog = {
            dateRangePickerState.selectedEndDateMillis?.let { createTripViewModel.onEndDateChange(it) }
            dateRangePickerState.selectedStartDateMillis?.let {
                createTripViewModel.onStartDateChange(
                    it
                )
            }
            dateRangePickerState.selectedStartDateMillis?.let {
                createTripViewModel.onEndDateChange(
                    it
                )
            }
        },
        members = members,
        valueName = valueName,
        isDialogOpen = isDialogOpen,
        onValueNameChange = { createTripViewModel.onValueNameChange(it) },
        onDialogOpenChange = { createTripViewModel.onDialogOpenChange(it) },
        onAddMember = { createTripViewModel.onAddMember(it) },
        onRemoveMember = { createTripViewModel.onRemoveMember(it) },
        stops = stops,
        onAddStop = { createTripViewModel.onAddStop(it) },
        onRemoveStop = { createTripViewModel.onRemoveStop(it) },
        transports = transports,
        isExpanded = isExpanded,
        onIsExpandedChange = { createTripViewModel.onIsExpandedChange(it) },
        transport = transport,
        onTransportChange = { createTripViewModel.onTransportChange(it) },
        description = description,
        onDescriptionChange = { createTripViewModel.onDescriptionChange(it) },
        onCreateTripClick = { createTripViewModel.onCreateTripClick() },
        originFocusRequester = originFocusRequester,
        destinationFocusRequester = destinationFocusRequester,
        membersFocusRequester = membersFocusRequester,
        descriptionFocusRequester = descriptionFocusRequester
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripFormContent(
    innerPadding: PaddingValues,
    tripName: String,
    onNameChange: (String) -> Unit,
    origin: String,
    onOriginChange: (String) -> Unit,
    destination: String,
    onDestinationChange: (String) -> Unit,
    startDate: Long,
    endDate: Long,
    dateRangePickerState: DateRangePickerState,
    showDateRangePickerDialog: Boolean,
    onShowDateRangePickerDialog: (Boolean) -> Unit,
    onConfirmDateRangePickerDialog: () -> Unit,
    members: List<String>,
    valueName: String,
    isDialogOpen: Boolean,
    onValueNameChange: (String) -> Unit,
    onDialogOpenChange: (Boolean) -> Unit,
    onAddMember: (String) -> Unit,
    onRemoveMember: (String) -> Unit,
    stops: List<String>,
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
    membersFocusRequester: FocusRequester,
    descriptionFocusRequester: FocusRequester
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(8.dp)
    ) {
        item {
            // Nombre del Viaje
            TextInputField(
                label = "Nombre del Viaje",
                textValue = tripName,
                onValueChange = onNameChange,
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Origen y Destino
            TextInputField(
                label = "Origen",
                textValue = origin,
                onValueChange = onOriginChange,
                focusRequester = destinationFocusRequester,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(4.dp))

            TextInputField(
                label = "Destino",
                textValue = destination,
                onValueChange = onDestinationChange,
                focusRequester = membersFocusRequester,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(4.dp))

            //Fechas
            DateRangePickerInput(
                label = "Fechas",
                startDate = startDate,
                endDate = endDate,
                dateRangePickerState = dateRangePickerState,
                showDateRangePicker = showDateRangePickerDialog,
                onDismiss = onShowDateRangePickerDialog,
                onConfirm = onConfirmDateRangePickerDialog
            ) { onShowDateRangePickerDialog(true) }

            Spacer(modifier = Modifier.size(4.dp))

            // Transporte
            ExposedDropdownMenuBoxInput(
                label = "Transporte",
                values = transports,
                isExpanded = isExpanded,
                transport = transport,
                onExpanded = onIsExpandedChange,
                onClickable = onTransportChange
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Descripción Opcional
            TextInputField(
                label = "Descripción (Opcional)",
                textValue = description,
                onValueChange = onDescriptionChange,
                focusRequester = descriptionFocusRequester,
                imeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Integrantes
            AddList(
                label = "Acompañantes",
                name = valueName,
                values = members,
                isDialogOpen = isDialogOpen,
                onValueNameChange = { onValueNameChange(it) },
                onDialogOpenChange = { onDialogOpenChange(it) },
                onAdd = onAddMember,
                onRemove = onRemoveMember
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Paradas
            AddList(
                label = "Puntos de Parada",
                name = valueName,
                values = stops,
                isDialogOpen = isDialogOpen,
                onValueNameChange = { onValueNameChange(it)},
                onDialogOpenChange = { onDialogOpenChange(it) },
                onAdd = onAddStop,
                onRemove = onRemoveStop
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Botón para guardar
            Button(
                onClick = onCreateTripClick,
                modifier = Modifier
                    .fillMaxWidth()
                //.align(Alignment.CenterHorizontally)
            ) {
                Text("Guardar")
            }
        }
    }
}