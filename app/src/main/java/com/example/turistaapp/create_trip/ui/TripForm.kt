package com.example.turistaapp.create_trip.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.ui.components.AddList
import com.example.turistaapp.create_trip.ui.components.DateRangePickerInput
import com.example.turistaapp.create_trip.ui.components.ExposedDropdownMenuBoxInput
import com.example.turistaapp.create_trip.ui.components.TextInputField
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripForm(innerPadding: PaddingValues) {
    //Nombre del Viaje
    var tripName by remember { mutableStateOf(TextFieldValue()) }
    //Origen y Destino
    var origin by remember { mutableStateOf(TextFieldValue()) }
    var destination by remember { mutableStateOf(TextFieldValue()) }
    //Fechas
    val calendar = Calendar.getInstance()
    var startDate by remember { mutableStateOf(calendar.timeInMillis) }
    var endDate by remember { mutableStateOf(calendar.timeInMillis) }
    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate,
        initialSelectedEndDateMillis = endDate
    )
    var showDateRangePickerDialog by remember { mutableStateOf(false) }
    //Members
    var members = remember { mutableStateListOf<String>() }
    //Paradas
    var stops = remember { mutableStateListOf<String>() }
    //Transporte
    val transports by remember {
        mutableStateOf(
            listOf(
                "Auto",
                "Moto",
                "Transporte Público",
                "A pie",
                "Bicicleta"
            )
        )
    }
    var isExpanded by remember { mutableStateOf(false) }
    var transport by remember { mutableStateOf("") }
    //Descripción
    var description by remember { mutableStateOf(TextFieldValue()) }
    //Focus Requesters
    val originFocusRequester = remember { FocusRequester() }
    val destinationFocusRequester = remember { FocusRequester() }
    val membersFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

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
                onValueChange = { tripName = it },
                focusRequester = originFocusRequester,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Origen y Destino
            TextInputField(
                label = "Origen",
                textValue = origin,
                onValueChange = { origin = it },
                focusRequester = destinationFocusRequester,
                imeAction = ImeAction.Next
            )

            Spacer(modifier = Modifier.size(4.dp))

            TextInputField(
                label = "Destino",
                textValue = destination,
                onValueChange = { destination = it },
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
                onDismiss = { showDateRangePickerDialog = false },
                onConfirm = {
                    showDateRangePickerDialog = false
                    startDate = dateRangePickerState.selectedStartDateMillis?: startDate
                    endDate = dateRangePickerState.selectedEndDateMillis?: endDate
                },
                onClickable = { showDateRangePickerDialog = true }
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Transporte
            ExposedDropdownMenuBoxInput(
                label = "Transporte",
                values = transports,
                isExpanded = isExpanded,
                transport = transport,
                onExpanded = { isExpanded = it },
                onClickable = { transport = it }
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Descripción Opcional
            TextInputField(
                label = "Descripción (Opcional)",
                textValue = description,
                onValueChange = { description = it },
                focusRequester = descriptionFocusRequester,
                imeAction = ImeAction.Done
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Integrantes
            AddList(
                label = "Miembros",
                values = members
            )

            Spacer(modifier = Modifier.size(4.dp))

            // Lista de Paradas
            AddList(
                label = "Paradas",
                values = stops
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Botón para guardar
            Button(
                onClick = {
                    // Realiza aquí la acción deseada cuando se presiona el botón.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    //.align(Alignment.CenterHorizontally)
            ) {
                Text("Guardar")
            }
        }


    }
}