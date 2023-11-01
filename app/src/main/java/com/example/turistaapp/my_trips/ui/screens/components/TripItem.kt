package com.example.turistaapp.my_trips.ui.screens.components

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
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.ui.theme.TuristaAppTheme
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.Calendar

@Composable
fun TripItem(
    trip: TripModel,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomStart,
        ) {
            AsyncImage(
                model = trip.destination.photoUrl,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(0.dp, 300.dp) // mention max width here
                    .heightIn(0.dp, 150.dp), // mention max height here
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            )
            // Headline (Title)
            Text(
                text = trip.name,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp),
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                color = Color.White,
            )
        }
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
    }
}

fun formatMilisToDateString(milisegundosString: String, formato: String): String {
    val milisegundos = milisegundosString.toLong()
    val dateTime = DateTime(milisegundos)
    val formatter = DateTimeFormat.forPattern(formato)
    return formatter.print(dateTime)
}

@Preview
@Composable
fun TripItemPreview() {
    val origin = LocationModel(
        placeID = "placeID",
        name = "Google Sydney - Pirrama Road",
        address = "address A",
        rating = 4.5,
        userRating = 4,
        lat = 0.0,
        lng = 0.0,
        photoUrl = "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Ftodofondos.com%2Fbin%2Ffondos%2F07%2F15%2F19d.jpg&f=1&nofb=1&ipt=17f83c6505836d11e575c170c14c7362d30f2ce1369976101add2d71bfd3b8f6&ipo=images",
        types = listOf("types"),
        isFinished = false,
        tripName = "tripName",
        tripId = 0,
    )

    val destination = LocationModel(
        placeID = "placeID",
        name = "Avenida Siempre Viva 1234 - Moron",
        address = "address B",
        rating = 3.5,
        userRating = 3,
        lat = 0.0,
        lng = 0.0,
        photoUrl = "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Ftodofondos.com%2Fbin%2Ffondos%2F07%2F15%2F19d.jpg&f=1&nofb=1&ipt=17f83c6505836d11e575c170c14c7362d30f2ce1369976101add2d71bfd3b8f6&ipo=images",
        types = listOf("types"),
        isFinished = false,
        tripName = "tripName",
        tripId = 0,
    )

    val calendar: Calendar = Calendar.getInstance()

    val trip = TripModel(
        name = "Trip 1",
        origin = origin,
        destination = destination,
        startDate = "1698710400000",
        endDate = "1698969600000",
        transport = "driving",
        members = null,
        stops = null,
        description = "Un viaje espectacular para visitar un lugar hermoso en la ciudad",
        author = "author",
        images = null,
        comments = null,
        isFavorite = false,
        isFinished = false,
    )

    TuristaAppTheme {
        TripItem(trip)
    }
}
