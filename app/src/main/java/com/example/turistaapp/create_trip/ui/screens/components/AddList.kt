package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddList(
    name: String,
    label: String,
    values: List<String>,
    onValueNameChange: (String) -> Unit,
    onAdd: (String) -> Unit,
    onRemove: (Int) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    // Input para nombres de miembros
    OutlinedTextField(
        value = name,
        onValueChange = { onValueNameChange(it) },
        label = { Text("Agregar $label") },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onAdd(name)
                    }
                },
                modifier = Modifier.background(Color.Transparent),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    )

    Spacer(modifier = Modifier.size(4.dp))
    // Lista de nombres
    LazyRow {
        itemsIndexed(values) { index, name ->
            MemberInputChip(
                text = name,
                onDismiss = {
                    onRemove(index)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberInputChip(
    text: String,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return

    InputChip(
        onClick = {
            onDismiss()
        },
        label = { Text(text) },
        selected = enabled,
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize),
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(4.dp),
    )
}
