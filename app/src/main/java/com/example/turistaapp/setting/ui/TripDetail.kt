package com.example.turistaapp.setting.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.size.Size
import com.example.turistaapp.setting.colors.DarkGrayPlusPlus
import com.google.android.material.chip.Chip
import com.google.maps.android.compose.GoogleMap
import org.jetbrains.annotations.ApiStatus.Experimental

import java.nio.file.WatchEvent


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetail() {
    BottomSheetScaffold(
        sheetContent = {
            BottomScaffoldBody(
                "Titulo viaje",
                "27/10/2023",
                "30/10/2023",
                "Martin",
                "Name",
                "Address",
                "DRIVING",
                "Un viaje espectacular para conocer mis destinos favoritos",
                listOf(
                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
                ),
                listOf(
                    "Martin",
                    "Titi",
                    "Ariel"
                )
            )
        },
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = 100.dp,
        sheetContainerColor = DarkGrayPlusPlus
    )
    {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomScaffoldBody(
    titulo: String,
    fechaInicio: String,
    fechaFin: String,
    nombreDelUsuario: String,
    nombre: String,
    direccion: String,
    tipoTransporte: String,
    descripcionDelViaje: String,
    listaImagenes: List<String>,
    miembros: List<String>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.height(800.dp)  // aca es el largo del deslizante
        ) {
            LazyColumn {
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(text = titulo, fontSize = 26.sp, color = Color.White)

                    }
                    Spacer(modifier = Modifier.padding(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            "",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                        Text(text = "$fechaInicio  -", fontSize = 15.sp, color = Color.White)
                        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                        Text(text = fechaFin, fontSize = 15.sp, color = Color.White)

                        Spacer(modifier = Modifier.padding(horizontal = 25.dp))
                        Row {
                            Icon(
                                imageVector = Icons.Default.PermIdentity, contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text(
                                text = "Por $nombreDelUsuario",
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }
                    }

                    LazyRow() {
                        item {
                            AssistChip(
                                onClick = {},
                                label = {
                                    Icon(
                                        imageVector = Icons.Default.Save, contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                            .size(20.dp),
                                    )
                                    Text("Guardar", color = Color.White)
                                },
                                modifier = Modifier
                                    .padding(8.dp)

                            )
                            AssistChip(
                                onClick = {},
                                label = {
                                    Icon(
                                        imageVector = Icons.Default.Share, contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                            .size(20.dp),
                                    )
                                    Text("Compartir", color = Color.White)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                            AssistChip(
                                onClick = {},
                                label = {
                                    Icon(
                                        imageVector = Icons.Default.CameraEnhance,
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                            .size(20.dp),
                                    )
                                    Text("camara", color = Color.White)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                            )

                            AssistChip(
                                onClick = {},
                                label = {
                                    Icon(
                                        imageVector = Icons.Default.Delete, contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                            .size(20.dp),
                                    )
                                    Text("Eliminar", color = Color.White)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }


                    Spacer(modifier = Modifier.padding(7.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Origen y destino",
                            fontSize = 19.sp,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.weight(0.5f))
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = "Auto",
                            tint = Color.Cyan,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text(
                            text = tipoTransporte,
                            fontSize = 19.sp,
                            color = Color.White
                        )
                    }




                    Spacer(modifier = Modifier.padding(vertical = 10.dp))

                    Row {

                        Icon(
                            imageVector = Icons.Default.AddLocationAlt, contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 9.dp, vertical = 8.dp)
                                .size(40.dp),
                        )
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            ),
                            modifier = Modifier
                                .size(310.dp, height = 80.dp)
                                .fillMaxWidth()
                        ) {
                            Row(Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Default.ImageSearch,
                                    "",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(width = 43.dp, height = 60.dp)
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 1.dp)
                                ) {
                                    Text(
                                        text = nombre,
                                        fontSize = 23.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                    Text(
                                        text = direccion,
                                        fontSize = 19.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                                    Row {
                                        Text(
                                            text = "4,9",
                                            fontSize = 21.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                //arreglar xD
                                                .padding(10.dp)
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "",
                                            tint = Color.Yellow,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Default.AddLocationAlt, contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 9.dp, vertical = 8.dp)
                                .size(40.dp),
                        )
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            ),
                            modifier = Modifier
                                .size(310.dp, height = 80.dp)
                                .fillMaxWidth()
                        ) {
                            Row(Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Default.ImageSearch,
                                    "",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(width = 43.dp, height = 60.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 1.dp)
                                ) {

                                    Row {
                                        Text(
                                            text = nombre,
                                            fontSize = 23.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )

                                        Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                                        Row {
                                            Text(
                                                text = "4,1",
                                                fontSize = 21.sp,
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                            )
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                tint = Color.Yellow,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                            )
                                        }
                                    }
                                    Text(
                                        text = direccion,
                                        fontSize = 19.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(40.dp))
                    Text(
                        text = "Acerca de",
                        fontSize = 19.sp,
                        color = Color.White
                    )
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 13.dp)
                            .size(360.dp, height = 130.dp)

                    ) {

                        Text(
                            text = descripcionDelViaje,
                            modifier = Modifier,
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                    }
                    Spacer(modifier = Modifier.size(9.dp))
                    Row() {
                        Text(
                            text = "Imagenes",
                            modifier = Modifier,
                            color = Color.White,
                            fontSize = 26.sp,
                        )

                    }
                    //imagenes

                    LazyRow(
                        Modifier.fillMaxWidth()
                    ) {
                        items(listaImagenes) { imagen ->
                            AsyncImage(model = imagen, contentDescription = "")
                        }
                    }
                    Spacer(modifier = Modifier.size(9.dp))
                    Text(
                        text = "Miembros",
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 26.sp,
                    )
                    LazyRow() {
                        items(miembros) { chipValue ->
                            AssistChip(
                                onClick = {},
                                label = { Text(chipValue, color = Color.White) },
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

        }

    }
}