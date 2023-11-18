package com.example.turistaapp.setting.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
    ) {
//        SettingUser()
//        Spacer(modifier = Modifier.size(21.dp))
        // Divider( thickness = 2.dp , modifier = Modifier.padding(4.dp))
        SettingAppearance { changeTheme() }
        Spacer(modifier = Modifier.size(16.dp))
        About()

//        Spacer(modifier = Modifier.size(21.dp))
//        SettingMore("MAS")
        Box(
            contentAlignment = BottomCenter,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            SettingVersion()
        }
    }
}

@Composable
fun SettingAppearance(changeTheme: () -> Unit) {
    var checked by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(
            text = "Aparencia",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (!checked) {
                Text(
                    "Modo oscuro",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(5f)
                        .align(CenterVertically),
                )
            } else {
                Text(
                    "Modo claro",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(5f)
                        .align(CenterVertically),
                )
            }

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
fun SettingVersion() {
    Text(
        "Version 1.1",
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun About() {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
        ) {
            Text("Conoce nuestro equipo", fontSize = 24.sp)
            Icon(
                imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }

        if (isExpanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(),
            ) {
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        ),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        modifier = Modifier
                            .fillParentMaxHeight(0.98f),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Green)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Red)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Blue)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                    }
                }
                /*
                item {
                    Text(
                        text = "Somos un pequeño grupo de estudiantes apasionados por el desarrollo de aplicaciones móviles modernas e intuitivas\n" +
                            "En TripOut te ofrecemos la posibilidad de planificar tus viajes de la manera mas organizada posible, brindandote todas las herramientas necesarias para cumplir con tus objetivos.\n" +
                            "Prepárate para embarcarte en un viaje único con TripOut. ¡Tu próxima aventura comienza aquí!\n" +
                            "Martín, Gabriel y Ariel\n" +
                            "Equipo de TripOut",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                 */
            }
        }
    }
}
