package com.example.turistaapp.my_trips.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.qr_code.ui.QRDialog
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripItem(
    trip: TripModel,
    isDialogOpen: Boolean,
    selectedDataQR: String,
    onQRButtonClick: () -> Unit,
    onDismissDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = modifier,
        ) {
            ImageWithBrush(
                name = trip.name,
                photoUrl = trip.destination.photoUrl ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(0.dp, 300.dp) // mention max width here
                    .heightIn(0.dp, 120.dp), // mention max height here)
            )

            ItemText(
                icon = Icons.Default.CalendarMonth,
                name = "${
                    formatMilisToDateString(
                        trip.startDate,
                        "dd/MM/yyyy",
                    )
                } - ${formatMilisToDateString(trip.endDate, "dd/MM/yyyy")}",
            )

            ItemText(
                icon = Icons.Default.TripOrigin,
                name = trip.origin.name,
            )

            ItemText(
                icon = Icons.Default.Flag,
                name = trip.destination.name,
            )
        }
        Spacer(modifier = Modifier.heightIn(4.dp))

        var menuAnchor by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = menuAnchor,
            onExpandedChange = { menuAnchor = !menuAnchor },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopEnd),
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .menuAnchor()
                    .align(Alignment.TopEnd),
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options Button",
                )
                DropdownMenu(expanded = menuAnchor, onDismissRequest = { menuAnchor = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Compartir") },
                        onClick = { onQRButtonClick() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.QrCode,
                                contentDescription = "QR Button",
                                tint = Color.White,
                            )
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Borrar") },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Button",
                                tint = Color.Red,
                            )
                        },
                    )
                }
            }
        }

        if (isDialogOpen) {
            QRDialog(
                onDismiss = { onDismissDialog() },
                data = selectedDataQR,
            )
        }
    }
}

fun formatMilisToDateString(milisegundosString: String, formato: String): String {
    val milisegundos = milisegundosString.toLong()
    val dateTime = DateTime(milisegundos)
    val formatter = DateTimeFormat.forPattern(formato)
    return formatter.print(dateTime)
}

@Composable
fun <T> ImageWithBrush(
    modifier: Modifier = Modifier,
    name: String,
    photoUrl: T,
    padding: Dp = 0.dp,
    textCenter: Boolean = false,
) {
    Box(
        modifier = Modifier.padding(padding),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "Translated description of what the image contains",
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
        // Headline (Title)
        Row(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black))),
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 4.dp),
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                color = Color.White,
                textAlign = if (textCenter) TextAlign.Center else TextAlign.Start,
            )
        }
    }
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .pointerInput(interactionSource, enabled) {
                detectTapGestures(
                    onPress = { onClick() },
                    onLongPress = { onClick() },
                )
            },
    ) {
        content()
    }
}

@Composable
fun ItemText(icon: ImageVector, name: String) {
    Row(
        modifier = Modifier
            .padding(8.dp),
    ) {
        Icon(imageVector = icon, contentDescription = name)
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 8.dp),
        )
    }
}
