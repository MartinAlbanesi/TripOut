package com.example.turistaapp.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(darkTheme: Boolean = false, changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
//     verticalAlignment = Alignment.CenterVertically

    ) {
//        SettingUser()
//        Spacer(modifier = Modifier.size(21.dp))
        // Divider( thickness = 2.dp , modifier = Modifier.padding(4.dp))
        SettingAppearance(darkTheme) { changeTheme() }
//        Spacer(modifier = Modifier.size(21.dp))
//        SettingMore("MAS")
        Spacer(modifier = Modifier.size(15.dp))
        About()
        Spacer(modifier = Modifier.size(560.dp))
        SettingVersion()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingAppearance(darkTheme: Boolean, changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(text = "Aparencia", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Row(
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                "modo oscuro",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(5f)
                    .align(CenterVertically),
            )
            var checked by rememberSaveable { mutableStateOf(true) }

            Switch(
                checked = checked,
                onCheckedChange = {
                    changeTheme()
                    checked = it
                },
                modifier = Modifier.weight(1f),
            )
        }
    }
}


@Composable
fun About() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(text = "Acerca de nosotros", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "¡Bienvenido a TripOut, tu compañero de viaje!",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "En TripOut, nos apasiona descubrir el mundo . Somos alumnos de la Universidad de la Matanza",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "¿Te preguntas por dónde empezar tu próximo viaje? Deja que TripOut te guíe con sugerencias adaptadas a tus intereses y preferencias. Ya sea que busques escapadas relajantes o aventuras llenas de adrenalina.",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Descubre nuevos horizontes, conéctate con otros apasionados por los viajes y comparte tus propias historias. En TripOut, creemos que cada viaje es una oportunidad para enriquecer nuestras vidas y crear recuerdos duraderos.",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Prepárate para embarcarte en un viaje único con TripOut. ¡Tu próxima aventura comienza aquí!",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "¡Vamos juntos a explorar el mundo!",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Martin, Gabriel y Ariel\nCreadores de TripOut",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingVersion() {
    Text(
        "Version 1.1",
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}
