package com.example.turistaapp.setting.ui.components

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.setting.utils.intentLinkedin

@Composable
fun AboutUsContent(
    context: Context,
) {
    LazyColumn {
        item {
            Text(
                text = "Somos un pequeño grupo de estudiantes apasionados por el desarrollo de aplicaciones móviles modernas e intuitivas\n" +
                        "En TripOut te ofrecemos la posibilidad de planificar tus viajes de la manera mas organizada posible, brindandote todas las herramientas necesarias para cumplir con tus objetivos.\n" +
                        "Prepárate para embarcarte en un viaje único con TripOut. ¡Tu próxima aventura comienza aquí!\n" +
                        "Equipo de TripOut" +
                        "\n\n Contactanos:",
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            TextButton(onClick = {
                intentLinkedin(context, "martin-albanesi")
            }) {
                Text(text = "Albanesi Martin")
            }
            TextButton(onClick = {
                intentLinkedin(context, "gabrielgomezgg")
            }) {
                Text(text = "Gomez Gabriel")
            }
            TextButton(onClick = {
                intentLinkedin(context, "ariel-eduardo-nappio-7840071a4")
            }) {
                Text(text = "Nappio Ariel")
            }
        }
    }
}