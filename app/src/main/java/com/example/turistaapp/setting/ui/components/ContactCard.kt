package com.example.turistaapp.setting.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.turistaapp.setting.utils.intentGithub
import com.example.turistaapp.setting.utils.intentLinkedin

@Composable
fun ContactCard(
    context: Context,
    name: String,
    email: String,
    linkedin: String,
    github: String,
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = image,
            contentDescription = "Contact image",
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth(),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                TextButton(onClick = {
                    intentLinkedin(context, linkedin)
                }) {
                    Text(text = "Linkedin")
                }
                TextButton(onClick = {
                    intentGithub(context, github)
                }) {
                    Text(text = "Github")
                }
            }
        }
    }
}
