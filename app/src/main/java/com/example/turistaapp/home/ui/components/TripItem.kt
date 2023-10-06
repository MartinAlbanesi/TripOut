package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.turistaapp.R

/**
 * TripItem es un item que se muestra en el BottomSheet
 * @param image: Painter
 * @param modifier: Modifier
 */

@Composable
fun /*<T>*/ TripItem(
    name : String,
    image: String,
    modifier: Modifier = Modifier,
) {
    Card {
        Text(text = name)
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.FillBounds,
//            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
        )
    }
}