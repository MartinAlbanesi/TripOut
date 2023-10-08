package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.home.domain.models.NearbyLocation

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    nearbyLocations: List<NearbyLocation>,
    onClickCard: (String) -> Unit
) {
    Column(
        Modifier.padding(
            bottom = paddingValues.calculateBottomPadding(),
            start = 4.dp, end = 4.dp
        )
    ) {
        Text(text = "Descubre más viajes", modifier = Modifier.padding(8.dp))

        LazyRow(modifier = Modifier.padding(4.dp)) {
            items(nearbyLocations) {
                TripItem(
                    name = it.name,
                    photoUrl = it.photoUrl,
                    onClickCard = { onClickCard(it.name) }
                )
            }
        }
    }
}