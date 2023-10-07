package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TripDialog(
    title: String,
    km: String = "",
    image: Painter,
    isShow: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isShow) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                        Text(text = title)
                        Text(text = km)
                        Image(
                            image, contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(5f)
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

                Icon(
                    Icons.Outlined.Cancel,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { onDismiss() }
                )

            }
        }
    }
}