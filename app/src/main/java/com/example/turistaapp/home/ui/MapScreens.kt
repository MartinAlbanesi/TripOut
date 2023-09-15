package com.example.turistaapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@Composable
fun MapScreen(
    mapUiSettings: MapUiSettings,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {

        GoogleMap(
            uiSettings = mapUiSettings,
        )

        //Barra Superior
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0x1A000000))
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = "Mis Destinos",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(9f),
                color = Color.Black,
                fontSize = 24.sp
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically)
                    .weight(1f),
            ) {
                Icon(
                    painterResource(id = R.drawable.fullscreen), contentDescription = "Fullscreen",
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(24.dp)
                    .align(CenterVertically)
                    .weight(1f),
            ) {
                Icon(
                    painterResource(id = R.drawable.tune), contentDescription = "Filter",
                    tint = Color.Black
                )
            }
        }
    }
}