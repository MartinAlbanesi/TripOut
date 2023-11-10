package com.example.turistaapp.map.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.turistaapp.R
import com.example.turistaapp.core.ui.components.shimmerBrush

@Composable
fun TripItem(
    name: String,
    photoUrl: String?,
    rating: Double? = null,
    userRating: Int? = null,
    address: String? = null,
    modifier: Modifier = Modifier,
) {

    val showShimmer = remember { mutableStateOf(true) }
    Card(
        modifier = modifier,
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        if (rating != null && userRating != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            ) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "$rating")
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "( $userRating )")
            }
            Text(text = "$address", modifier = Modifier.padding(horizontal = 8.dp))
        }
        SubcomposeAsyncImage(
            model = photoUrl ?: R.drawable.placeholder,
            contentDescription = name,
            contentScale = ContentScale.FillBounds,
            // placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp)),
//            loading = {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//                    CircularProgressIndicator(modifier = Modifier.size(80.dp))
//                }
//            },
            onSuccess = {showShimmer.value = false}
        )
    }
}
