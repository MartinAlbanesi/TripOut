package com.example.turistaapp.create_trip.ui.screens.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
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
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        label = { Text(label) },
        singleLine = true,
        maxLines = 1,
        value = query,
        onValueChange = {
            onQueryChange(it)
            onDropdownVisibilityChange(true)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
    )

    predictions.forEach { prediction ->
        val description = prediction.description
        val distance = "Distancia: XXX km" // Reemplaza XXX con la distancia real si tienes esa información disponible
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onPredictionSelect(prediction)
                    onDropdownVisibilityChange(false)

                }
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = description ?: "",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = distance,
                    style = TextStyle(color = Color.Gray)
                )
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