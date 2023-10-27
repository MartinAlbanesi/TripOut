package com.example.turistaapp.map.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.domain.models.LocationModel

@Composable
fun NearbySearchView(
    modifier: Modifier = Modifier,
    nearbyLocations: List<LocationModel>,
    onClickCard: (String) -> Unit,
) {
    LazyRow(modifier = Modifier.padding(4.dp)) {
        items(nearbyLocations) {
            TripItem(
                name = it.name,
                photoUrl = it.photoUrl,
                modifier = Modifier
                    .size(240.dp, 360.dp)
                    .padding(4.dp)
                    .clickable { onClickCard(it.name) }
            )
        }
    }
}
