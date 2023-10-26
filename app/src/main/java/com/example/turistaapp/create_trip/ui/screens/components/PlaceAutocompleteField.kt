package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceAutocompleteField(
    label: String,
    query: String,
    onQueryChange: (String) -> Unit,
    isDropdownVisible: Boolean,
    onDropdownVisibilityChange: (Boolean) -> Unit,
    predictions: List<PlaceAutocompletePredictionModel>,
    onPredictionSelect: (PlaceAutocompletePredictionModel) -> Unit,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClearField: () -> Unit,
    onSelectedLocationChange: (PlaceAutocompletePredictionModel) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        label = { Text(label) },
        singleLine = true,
        maxLines = 1,
        value = query,
        onValueChange = {
            onQueryChange(it)
            if (it.isEmpty()) {
                onDropdownVisibilityChange(false)
            } else {
                onDropdownVisibilityChange(true)
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClearField()
                        onDropdownVisibilityChange(false)
                    },
                    modifier = Modifier.background(Color.Transparent),
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        },

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
    )

    if (isDropdownVisible) {
        predictions.forEach { prediction ->
            val description = prediction.description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onPredictionSelect(prediction)
                        onDropdownVisibilityChange(false)
                        onSelectedLocationChange(prediction)
                    }
                    .padding(16.dp),
            ) {
                Column {
                    Text(
                        text = description ?: "",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )
                }
            }
        }
    }

    /*
    Log.d("PlaceAutocompleteField", "PlaceAutocompleteField: $isDropdownVisible")
    DropdownMenu(
        expanded = isDropdownVisible,
        onDismissRequest = {
            onDropdownVisibilityChange(false)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        predictions.forEach { prediction ->
            DropdownMenuItem(
                onClick = {
                    onPredictionSelect(prediction)
                },
                text = {
                    Log.d("PlaceAutocompleteField", "PlaceAutocompleteField: ${prediction.description}")
                    Text(text = prediction.description ?: "")
                }
            )
        }
    }

     */

    Spacer(modifier = Modifier.height(4.dp))
}
