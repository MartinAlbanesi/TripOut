package com.example.turistaapp.my_trips.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.qr_code.ui.QRDialog
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

@Composable
fun TripItem(
    trip: TripModel,
    isDialogOpen: Boolean,
    selectedDataQR: String,
    onQRButtonClick: () -> Unit,
    onDismissDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
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
        // Subhead (Date)
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        ) {
            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Trip Dates")
            Text(
                text = "${
                    formatMilisToDateString(
                        trip.startDate,
                        "dd/MM/yyyy",
                    )
                } - ${formatMilisToDateString(trip.endDate, "dd/MM/yyyy")}",
                modifier = Modifier
                    .padding(start = 8.dp),
            )
        }
        // Supporting Text (Origin - Destination)
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        ) {
            Icon(imageVector = Icons.Default.TripOrigin, contentDescription = "Origin Title")
            Text(
                text = trip.origin.name,
                modifier = Modifier
                    .padding(start = 8.dp),
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
        ) {
            Icon(imageVector = Icons.Default.Flag, contentDescription = "Destination Title")
            Text(
                text = trip.destination.name,
                modifier = Modifier
                    .padding(start = 8.dp),
            )
        }
        Spacer(modifier = Modifier.heightIn(8.dp))

        QRButton(
            onQRButtonClick = onQRButtonClick,
            isDialogOpen = isDialogOpen,
            onDismissDialog = { onDismissDialog() },
            selectedDataQR = selectedDataQR,
        )
    }
    Spacer(modifier = Modifier.heightIn(4.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRButton(
    onQRButtonClick: () -> Unit,
    isDialogOpen: Boolean,
    onDismissDialog: () -> Unit,
    selectedDataQR: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onQRButtonClick,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.QrCode,
                contentDescription = "QR Button",
                modifier = Modifier
                    .padding(end = 8.dp),
            )
            Text(
                text = "QR",
                modifier = Modifier
                    .padding(end = 8.dp),
            )
        }
    }

    if (isDialogOpen) {
        QRDialog(
            onDismiss = { onDismissDialog() },
            data = selectedDataQR,
        )
    }
}
