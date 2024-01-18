package com.example.turistaapp.my_trips.ui.screens.components

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Map
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.R
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.create_trip.utils.getCurrentDate
import com.example.turistaapp.qr_code.ui.QRDialog
import com.example.turistaapp.trip_details.ui.components.DialogDeleteTrip
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTripsItem(
    trip: TripModel,
    isDialogOpen: Boolean,
    selectedDataQR: String,
    onQRButtonClick: () -> Unit,
    onDeleteButtonClick: (TripModel) -> Unit,
    onDismissDialog: () -> Unit,
    onMapButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    var menuAnchor by remember { mutableStateOf(false) }

    Box {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = modifier.clickable {
                menuAnchor = !menuAnchor

            },
        ) {
            ImageWithBrush(
                name = trip.name,
                photoUrl = trip.destination.photoUrl ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(0.dp, 300.dp) // mention max width here
                    .heightIn(0.dp, 120.dp), // mention max height here)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                ItemText(
                    icon = Icons.Default.CalendarMonth,
                    name = "${trip.startDate} - ${trip.endDate}",
//                    name = "${
//                        formatMilisToDateString(
//                            trip.startDate,
//                            "dd/MM/yyyy",
//                        )
//                    } - ${formatMilisToDateString(trip.endDate, "dd/MM/yyyy")}",
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                ) {
                    val daysLeft = getDaysBetweenDates(trip.startDate, trip.endDate)
//                        formatMilisToDateString(trip.startDate, "yyyy-MM-dd"),
//                        formatMilisToDateString(
//                            Calendar.getInstance().timeInMillis.toString(),
//                            "yyyy-MM-dd",
//                        ),
//                    )
                    when {
                        daysLeft.toInt() < 0 -> Text(
                            text = stringResource(R.string.trip_is_over),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        daysLeft.toInt() == 0 -> Text(
                            text = stringResource(R.string.today),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        daysLeft.toInt() == 1 -> Text(
                            text = stringResource(R.string.tomorrow),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        else -> Text(
                            text = stringResource(R.string.in_days, daysLeft),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            ItemText(
                icon = Icons.Default.TripOrigin,
                name = trip.origin.name,
            )

            ItemText(
                icon = Icons.Default.Flag,
                name = trip.destination.name,
            )
        }

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
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    Color.Black,
                                    Color.Transparent,
                                ),
                                radius = 46f,
                            ),
                        ),
                )
                DropdownMenu(expanded = menuAnchor, onDismissRequest = { menuAnchor = false }) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.see_map)) },
                        onClick = { onMapButtonClick(trip.tripId) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = "Map Button",
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.share)) },
                        onClick = {
                            onQRButtonClick()
                            menuAnchor = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.QrCode,
                                contentDescription = Icons.Default.QrCode.name,
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        },
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.delete)) },
                        onClick = {
                            showDeleteDialog = true
                            menuAnchor = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = Icons.Default.Delete.name,
                                tint = MaterialTheme.colorScheme.error,
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

        if (showDeleteDialog) {
            DialogDeleteTrip(
                trip = trip,
                onDeleteTripButtonClick = {
                    onDeleteButtonClick(it)
                },
                onDismissRequest = {
                    showDeleteDialog = false
                },
            )
        }
    }
}

//fun formatMilisToDateString(milisegundosString: String, formato: String): String {
//    val milisegundos = milisegundosString.toLong()
//    val dateTime = DateTime(milisegundos)
//    val formatter = DateTimeFormat.forPattern(formato)
//    return formatter.print(dateTime.plusDays(1))
//}

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
            .padding(6.dp),
    ) {
        Icon(imageVector = icon, contentDescription = name)
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 8.dp),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysBetweenDates(startDate: String, endDate: String): String {
    // Convert the dates from String to LocalDate objects

    val df = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val firstDateObj = LocalDate.parse(startDate, df)
    val secondDateObj = LocalDate.parse(getCurrentDate(), df)

    // Calculate the difference in days
    val daysDiff = ChronoUnit.DAYS.between(secondDateObj, firstDateObj)

    // Return the difference in days
    return daysDiff.absoluteValue.toString()
}
