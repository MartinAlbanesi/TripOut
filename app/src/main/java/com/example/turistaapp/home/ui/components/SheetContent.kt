package com.example.turistaapp.home.ui.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.home.domain.models.NearbyLocation

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    nearbyLocationApis: List<NearbyLocation>
) {
    Card(modifier = modifier) {
        Text(text = "Descubre más viajes.", modifier = Modifier.padding(4.dp))
    }
}