package com.example.turistaapp.trip_details.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.turistaapp.create_trip.domain.models.TripModel

@Composable
fun DialogDeleteTrip(
    trip: TripModel,
    onDeleteTripButtonClick: (TripModel) -> Unit,
    onDismissRequest: (Boolean) -> Unit,
    onMarkerSelectChange: (Boolean) -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest(false)
        },
        title = {
            Text(text = "Eliminar viaje")
        },
        text = {
            Text(text = "¿Estas seguro que deseas eliminar este viaje?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onDeleteTripButtonClick(trip)
                    onDismissRequest(false)
                    onMarkerSelectChange(false)
                },
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest(false)
                },
            ) {
                Text(text = "Cancelar")
            }
        },
    )
}
