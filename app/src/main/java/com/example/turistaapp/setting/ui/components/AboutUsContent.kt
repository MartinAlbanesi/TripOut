package com.example.turistaapp.setting.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R

@Composable
fun AboutUsContent(
    context: Context,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
        ) {
            ContactCard(
                context = context,
                name = "Albanesi Martín",
                email = "martinalbanesi89@gmail.com",
                linkedin = "martin-albanesi",
                github = "martinalbanesi",
                image = painterResource(id = R.drawable.martin_albanesi_profile_picture),
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Divider()
            ContactCard(
                context = context,
                name = "Gomez Gabriel",
                email = "gabrielgomezgg1997@gmail.com",
                linkedin = "gabrielgomezgg",
                github = "gabrielgomezgg",
                image = painterResource(id = R.drawable.gabriel_gomez_profile_picture),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}
