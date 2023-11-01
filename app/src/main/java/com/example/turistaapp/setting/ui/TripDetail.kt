package com.example.turistaapp.setting.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.turistaapp.map.domain.models.RouteModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetails(
//    titulo: String,
//    fechaInicio: String,
//    fechaFin: String,
//    nombre: String,
//    direccion: String,
//    tipoTransporte: String,
//    descripcionDelViaje: String,
    routeModel: RouteModel?,
//    listaImagenes: List<String>?
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.height(800.dp)  // aca es el largo del deslizante
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = routeModel?.trip!!.name, fontSize = 26.sp, color = Color.White)

                Row(
                    modifier = Modifier
                        .padding(horizontal = 40.dp)

                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        "",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .padding(horizontal = 13.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.Delete,
                        "",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 13.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        "",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 13.dp)
                    )
                    // .align(alignment = )
                }
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

                Text(text = "${routeModel?.trip!!.startDate}  -", fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = routeModel.trip.endDate, fontSize = 20.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.padding(7.dp))

            Row {
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = "",
                    tint = Color.Cyan,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .size(50.dp)
                )
                Text(
                    text = routeModel?.trip!!.transport,
                    fontSize = 23.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                )

            }
            Spacer(modifier = Modifier.padding(7.dp))

            Row {

                Icon(
                    imageVector = Icons.Default.AddLocationAlt, contentDescription = "",
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
                            modifier = Modifier
                                .size(width = 43.dp, height = 60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(vertical = 1.dp)
                        ) {
                            Text(
                                text = routeModel?.trip!!.origin.name,
                                fontSize = 23.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            Text(
                                text = routeModel.trip.origin.address!!,
                                fontSize = 19.sp,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.AddLocationAlt, contentDescription = "",
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
                            modifier = Modifier
                                .size(width = 43.dp, height = 60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(vertical = 1.dp)
                        ) {
                            Text(
                                text = routeModel?.trip!!.destination.name,
                                fontSize = 23.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                            Text(
                                text = routeModel.trip.destination.address!!,
                                fontSize = 19.sp,
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
                    .padding(horizontal = 13.dp )
                    .size(360.dp, height = 130.dp)

            ) {

                Text(
                    text = routeModel?.trip!!.description ?: "",
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
                Icon(
                    imageVector = Icons.Default.Add,
                    "",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(height = 30.dp, width = 50.dp)
                )
            }
            //imagenes
            if(routeModel?.trip!!.images != null){
                LazyRow (Modifier.fillMaxWidth()) {
                    items(routeModel.trip.images!!.toList()) { imagen ->
                        AsyncImage(model = imagen, contentDescription = "")
                    }
                }
            }
        }
    }
}