package com.example.turistaapp.create_trip.ui.screens.create_trip.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turistaapp.ui.theme.TuristaAppTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripForm() {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
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
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Guardar")
        }
    }
}

@Preview
@Composable
fun TripFormPreview() {
    TuristaAppTheme {
        TripForm()
    }
}