package com.example.turistaapp.setting.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R

@Composable
fun LocationCard(
    icon: ImageVector,
    locationName: String,
    locationRating: Double,
    locationAddress: String?,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Location Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 4.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = locationName,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = locationAddress ?: stringResource(R.string.there_is_no_direction),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(y = (-8).dp, x = 8.dp)
                        .clip(shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(2.dp)
                ) {
                    Text(
                        text = locationRating.toString(),
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .align(Alignment.CenterVertically),
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}