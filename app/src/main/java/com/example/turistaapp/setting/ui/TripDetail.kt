package com.example.turistaapp.setting.ui

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.turistaapp.map.domain.models.RouteModel


//@Preview
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TripDetail() {
//    BottomSheetScaffold(
//        sheetContent = {
//            BottomScaffoldBody(
//                "Titulo viaje",
//                "27/10/2023",
//                "30/10/2023",
//                "Martin",
//                "Name",
//                "San justo",
//                "Unlam",
//                "Address",
//                "DRIVING",
//                "Un viaje espectacular para conocer mis destinos favoritos",
//                listOf(
//                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
//                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
//                    "https://i.pinimg.com/236x/5a/5f/d0/5a5fd0dc0f7810ef3aeaf58883d66113.jpg",
//                ),
//                listOf(
//                    "Martin",
//                    "Titi",
//                    "Ariel"
//                )
//            )
//        },
//        scaffoldState = rememberBottomSheetScaffoldState(),
//        sheetPeekHeight = 100.dp,
//        sheetContainerColor = DarkGrayPlusPlus
//    )
//    {
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetails(
//    titulo: String,
//    fechaInicio: String,
//    fechaFin: String,
//    nombreDelUsuario: String,
//    nombre: String,
//    valoracionIda : String,
//    valoracionFin : String,
//    direccion: String,
//    tipoTransporte: String,
//    descripcionDelViaje: String,
//    listaImagenes: List<String>,
//    miembros: List<String>
    routeModel: RouteModel? = null,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            modifier = Modifier.height(800.dp)  // aca es el largo del deslizante
//        ) {


    var selectedImageUris by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            selectedImageUris += uris.map { it.toString() }
            settingViewModel.updateImages(routeModel!!.trip!!.tripId, selectedImageUris)
        }
    )

    LaunchedEffect(routeModel) {
        Log.i("titi", routeModel?.trip?.images.toString())
    }

    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = routeModel!!.trip!!.name, fontSize = 26.sp)

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
                )
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = "${routeModel!!.trip!!.startDate}  -", fontSize = 15.sp)
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Text(text = routeModel.trip!!.endDate, fontSize = 15.sp)

                Spacer(modifier = Modifier.padding(horizontal = 25.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.PermIdentity, contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                            .size(20.dp),
                    )
                    Text(
                        text = "Por ${routeModel.trip.author}",
                        fontSize = 15.sp,
                    )
                }
            }

            LazyRow() {
                item {
                    AssistChip(
                        onClick = {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.ImageSearch, contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Imagen")
                        },
                        modifier = Modifier
                            .padding(8.dp)

                    )
                    AssistChip(
                        onClick = {},
                        label = {
                            Icon(
                                imageVector = Icons.Default.Share, contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Compartir")
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
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("camara")
                        },
                        modifier = Modifier
                            .padding(8.dp)
                    )

                    AssistChip(
                        onClick = {},
                        label = {
                            Icon(
                                imageVector = Icons.Default.Delete, contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Eliminar")
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
                )

                Spacer(modifier = Modifier.weight(0.5f))
                Icon(
                    imageVector = Icons.Default.DirectionsCar,
                    contentDescription = "Auto",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 5.dp))
                Text(
                    text = routeModel!!.trip!!.transport,
                    fontSize = 19.sp,
                )
            }




            Spacer(modifier = Modifier.padding(vertical = 10.dp))

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

                            Row {
                                Text(
                                    text = routeModel!!.trip!!.origin.name,
                                    fontSize = 23.sp,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )

                                Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                                Row {
                                    Text(
                                        text = routeModel!!.trip!!.origin.rating.toString(),
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                            }
                            Text(
                                text = routeModel!!.trip!!.origin.address.toString(),
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

                            Row {
                                Text(
                                    text = routeModel!!.trip!!.destination.name,
                                    fontSize = 23.sp,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )

                                Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                                Row {
                                    Text(
                                        text = routeModel.trip!!.destination.rating.toString(),
                                        fontSize = 21.sp,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                            }
                            Text(
                                text = routeModel!!.trip!!.destination.address.toString(),
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
            )
            Card(
                modifier = Modifier
                    .padding(horizontal = 13.dp)
                    .size(360.dp, height = 130.dp)

            ) {

                Text(
                    text = routeModel!!.trip!!.description.toString(),
                    modifier = Modifier,
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.size(9.dp))
            Row() {
                Text(
                    text = "Imagenes",
                    modifier = Modifier,
                    fontSize = 26.sp,
                )

            }
        }
        //imagenes

//            LazyRow(
//                Modifier.fillMaxWidth()
//            ) {
        item {
            LazyRow(
                Modifier.fillMaxWidth()
            ) {
                items(routeModel!!.trip!!.images ?: emptyList()) { imagen ->
//            Log.i("titi", imagen)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imagen)
                            .size(300, 300)
                            .crossfade(true)
                            .build(),
                        contentDescription = ""
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.size(9.dp))
            Text(
                text = "Miembros",
                modifier = Modifier,
                fontSize = 26.sp,
            )
        }
        items(routeModel!!.trip!!.members ?: emptyList()) { chipValue ->
            AssistChip(
                onClick = {},
                label = { Text(chipValue, color = Color.White) },
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

//    LazyRow() {
//        items(routeModel!!.trip!!.members ?: emptyList()) { chipValue ->
//            AssistChip(
//                onClick = {},
//                label = { Text(chipValue, color = Color.White) },
//                modifier = Modifier
//                    .padding(8.dp)
//            )
//        }
//    }
//}
//            }
//
//        }

//}
//}