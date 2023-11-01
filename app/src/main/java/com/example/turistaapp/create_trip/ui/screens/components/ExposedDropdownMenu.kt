package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiTransportation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxInput(
    label: String,
    values: List<String>,
    isExpanded: Boolean,
    transport: String = values[0],
    onExpanded: (Boolean) -> Unit,
    onClickable: (String) -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpanded(it) },
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = transport,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            placeholder = { Text("Selecciona un transporte") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.EmojiTransportation,
                    contentDescription = "Transporte",
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpanded(false) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            values.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onClickable(item)
                        onExpanded(false)
                    },
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
