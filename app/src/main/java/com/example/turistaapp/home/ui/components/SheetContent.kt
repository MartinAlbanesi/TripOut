package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.domain.models.LocationModel

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    nearbyLocations: List<LocationModel>,
    onClickCard: (String) -> Unit,
) {
    Column(
        Modifier.padding(
            bottom = paddingValues.calculateBottomPadding(),
            start = 4.dp,
            end = 4.dp,
        ),
    ) {
        Text(
            text = "Descubre más viajes",
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )

        LazyRow(modifier = Modifier.padding(4.dp)) {
            items(nearbyLocations) {
                TripItem(
                    name = it.name,
                    photoUrl = it.photoUrl,
                    modifier = Modifier
                        .size(240.dp, 360.dp)
                        .padding(4.dp).clickable { onClickCard(it.name) }
                )
            }
        }
    }
}
