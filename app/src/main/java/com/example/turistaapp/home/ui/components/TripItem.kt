package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.R

/**
 * TripItem es un item que se muestra en el BottomSheet
 * @param image: Painter
 * @param modifier: Modifier
 */

@Composable
fun TripItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(200.dp, 360.dp)
            .padding(4.dp)
            .clickable {
                onClickCard()
            },
    ) {
        Text(text = name, modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = photoUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier.fillMaxSize().padding(8.dp).clip(RoundedCornerShape(12.dp)),
        )
    }
}
