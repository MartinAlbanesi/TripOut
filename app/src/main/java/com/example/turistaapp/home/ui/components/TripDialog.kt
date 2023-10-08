package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage

@Composable
fun TripDialog(
    name: String,
    photoUrl: String,
    rating: Double = 0.0,
    userRating : Int = 0,
    direction : String = "",
    lat: Double = 0.0,
    lng: Double = 0.0,
    isShow: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isShow) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        Modifier.padding(8.dp)) {
                        Text(text = name, modifier = Modifier
                            .padding(4.dp)
                        )
                        AsyncImage(
                            model =  photoUrl,
                            contentDescription = name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Button(
                            onClick = { onConfirm() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Confirmar Viaje")
                        }
                    }
                }


                IconButton(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .align(Alignment.TopEnd))
                {
                    Icon(
                        Icons.Outlined.Cancel,
                        contentDescription = Icons.Outlined.Cancel.toString(),
                    )
                }

            }
        }
    }
}