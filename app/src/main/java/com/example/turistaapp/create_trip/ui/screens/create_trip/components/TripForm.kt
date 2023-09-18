package com.example.turistaapp.create_trip.ui.screens.create_trip.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turistaapp.ui.theme.TuristaAppTheme
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
    val preferredTransportFocusRequester = remember { FocusRequester() }
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

        ExposedDropdownMenuBoxInput(
            label = "Transporte"
        )

        // Descripción Opcional
        TextInputField(
            label = "Descripción (Opcional)",
            textValue = description,
            onValueChange = { description = it },
            focusRequester = descriptionFocusRequester,
            imeAction = ImeAction.Done
        )

        // Lista de Integrantes
        AddMemberList()

        // Lista de Paradas
        TextInputField(
            label = "Paradas",
            textValue = stops,
            onValueChange = { stops = it },
            focusRequester = preferredTransportFocusRequester,
            imeAction = ImeAction.Next
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

@Composable
fun TextInputField(
    label: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = textValue,
        singleLine = true,
        maxLines = 1,
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
            .focusRequester(focusRequester)
    )
    Spacer(modifier = Modifier.height(4.dp))
}

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
        }, year, month, day
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
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                readOnly = true,
                label = { Text(label) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                datePicker.show()
                            }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxInput(label: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var transport by remember { mutableStateOf("") }
    val transportList = arrayOf("Auto", "Moto", "Transporte Público", "A pie", "Bicicleta")

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = transport,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            placeholder = { Text("Selecciona un transporte") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            transportList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        transport = item
                        isExpanded = false
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun AddMemberList() {
    var name by remember { mutableStateOf("") }
    val names = remember { mutableStateListOf<String>() }

    var isDialogOpen by remember { mutableStateOf(false) }

    // Fila con botón de agregar y lista de nombres
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón para abrir el cuadro de diálogo
        IconButton(
            onClick = { isDialogOpen = true },
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        Spacer(modifier = Modifier.size(8.dp))
        // Lista horizontal de nombres
        LazyRow {
            items(names) { name ->
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }

    // Cuadro de diálogo para ingresar el nombre
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                isDialogOpen = false
                name = ""
            },
            title = { Text("Agregar Nombre") },
            text = {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            names.add(name)
                            name = ""
                            isDialogOpen = false
                        }
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isDialogOpen = false
                        name = ""
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}


@Preview
@Composable
fun TripFormPreview() {
    TuristaAppTheme {
        TripForm()
    }
}