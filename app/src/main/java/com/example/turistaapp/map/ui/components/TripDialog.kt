package com.example.turistaapp.map.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.turistaapp.R
import com.example.turistaapp.create_trip.domain.models.LocationModel

@Composable
fun TripDialog(
//    name: String,
//    photoUrl: String?,
//    rating: Double? = null,
//    userRating: Int? = null,
//    address: String? = null,
    nearbyLocationSelect: LocationModel,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
        ) {
            Column(
                Modifier.padding(8.dp),
            ) {
                TripItem(
                    name = nearbyLocationSelect.name,
                    photoUrl = nearbyLocationSelect.photoUrl,
                    rating = nearbyLocationSelect.rating,
                    userRating = nearbyLocationSelect.userRating,
                    address = nearbyLocationSelect.address,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
                Button(
                    onClick = { onConfirm("${nearbyLocationSelect.name}, ${nearbyLocationSelect.address}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(text = stringResource(R.string.confirm_trip))
                }
            }

            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 12.dp, y = (-12).dp)
            ) {
                Icon(
                    Icons.Outlined.Cancel,
                    contentDescription = Icons.Outlined.Cancel.toString(),
                    Modifier.size(40.dp)
                )
            }
        }
    }
}
