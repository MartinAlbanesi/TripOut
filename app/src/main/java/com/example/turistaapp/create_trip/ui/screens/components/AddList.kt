package com.example.turistaapp.create_trip.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddList(
    label: String,
    values: List<String>,
    onAdd: (String) -> Unit,
    onRemove: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var isDialogOpen by remember { mutableStateOf(false) }

    // Fila con botón de agregar y lista de nombres
    Text(text = label)
    Spacer(modifier = Modifier.size(4.dp))
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

        Spacer(modifier = Modifier.size(4.dp))
        // Lista horizontal de nombres
        LazyRow {
            items(values) { name ->
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .background(MaterialTheme.colorScheme.secondary)
                        .wrapContentHeight(Alignment.CenterVertically)
                ){
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)
                    )
                    IconButton(
                        onClick = { onRemove(name) },
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }

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
            title = { Text("Agregar $label") },
            text = {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            onRemove(name)
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
