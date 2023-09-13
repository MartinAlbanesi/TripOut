package com.example.turistaapp.create_trip.ui.screens.components

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turistaapp.ui.theme.TuristaAppTheme
import java.util.Calendar
import androidx.compose.runtime.*
import java.util.*

@Composable
fun TripForm() {
    var tripName by remember { mutableStateOf(TextFieldValue()) }
    var origin by remember { mutableStateOf(TextFieldValue()) }
    var destination by remember { mutableStateOf(TextFieldValue()) }
    var members by remember { mutableStateOf(TextFieldValue()) }
    var stops by remember { mutableStateOf(TextFieldValue()) }
    var preferredTransport by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    val originFocusRequester = remember { FocusRequester() }
    val destinationFocusRequester = remember { FocusRequester() }
    val membersFocusRequester = remember { FocusRequester() }
    val stopsFocusRequester = remember { FocusRequester() }
    val preferredTransportFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nombre del Viaje
        TextInputField(
            label = "Nombre del Viaje",
            textValue = tripName,
            onValueChange = { tripName = it },
            focusRequester = originFocusRequester,
            imeAction = ImeAction.Next
        )

        // Origen y Destino
        TextInputField(
            label = "Origen",
            textValue = origin,
            onValueChange = { origin = it },
            focusRequester = destinationFocusRequester,
            imeAction = ImeAction.Next
        )
        TextInputField(
            label = "Destino",
            textValue = destination,
            onValueChange = { destination = it },
            focusRequester = membersFocusRequester,
            imeAction = ImeAction.Next
        )

        // Fechas
        DateInputField(
            label = "Desde"
        )

        DateInputField(
            label = "Hasta"
        )

        // Lista de Integrantes
        TextInputField(
            label = "Integrantes",
            textValue = members,
            onValueChange = { members = it },
            focusRequester = stopsFocusRequester,
            imeAction = ImeAction.Next
        )

        // Lista de Paradas
        TextInputField(
            label = "Paradas",
            textValue = stops,
            onValueChange = { stops = it },
            focusRequester = preferredTransportFocusRequester,
            imeAction = ImeAction.Next
        )

        // Transporte Preferido
        TextInputField(
            label = "Transporte Preferido",
            textValue = preferredTransport,
            onValueChange = { preferredTransport = it },
            focusRequester = descriptionFocusRequester,
            imeAction = ImeAction.Next
        )

        // Descripción Opcional
        TextInputField(
            label = "Descripción (Opcional)",
            textValue = description,
            onValueChange = { description = it },
            focusRequester = descriptionFocusRequester,
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(16.dp))

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    label: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = {
                onValueChange(it)
            },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(focusRequester)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputField(label: String) {
    var date by remember {
        mutableStateOf("")
    }

    val calendar = Calendar.getInstance()

    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)

    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _, aYear, aMonth, aDayOfMonth ->
            date = "$aDayOfMonth/${aMonth + 1}/$aYear"
        },year, month, day
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clickable {
                datePicker.show()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                readOnly = true,
                label = { Text(label)},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(4.dp)
                            .clickable{
                                datePicker.show()
                            }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

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