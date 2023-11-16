package com.example.turistaapp.setting.ui

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EmojiTransportation
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.domain.models.RouteModel
import com.example.turistaapp.my_trips.ui.screens.components.formatMilisToDateString

@Composable
fun TripDetails(
    routeModel: RouteModel? = null,
    settingViewModel: SettingViewModel = hiltViewModel(),
    onClickQR: () -> Unit,
    onDeleteTripButtonClick: (TripModel) -> Unit,
) {
    var selectedImageUris by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            selectedImageUris += uris.map { it.toString() }
            settingViewModel.updateImages(routeModel!!.trip!!.tripId, selectedImageUris)
        },
    )

    var deniedPermission by remember {
        mutableStateOf(false)
    }

    var isShowDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            multiplePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
            )
        } else {
            if (!deniedPermission) {
                deniedPermission = true
                isShowDialog = true
            } else {
//                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
    }

    LaunchedEffect(routeModel) {
        Log.i("titi", routeModel?.trip?.images.toString())
    }

    DialogShouldShowRationale(isShowDialog = isShowDialog) {
        isShowDialog = !isShowDialog
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = routeModel!!.trip!!.name,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.6f),
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            "Trip Dates",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                        Text(
                            text = "${
                                formatMilisToDateString(
                                    routeModel?.trip!!.startDate,
                                    "dd/MM/yy",
                                )
                            } - ",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        )
                        Text(
                            text = formatMilisToDateString(
                                routeModel.trip.endDate,
                                "dd/MM/yy",
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f),
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.PermIdentity,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                                .size(20.dp)
                                .align(Alignment.CenterVertically),
                        )
                        Text(
                            text = "Por ${routeModel?.trip?.author ?: "Nadie"}",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        )
                    }
                }
            }

            LazyRow() {
                item {
                    AssistChip(
                        onClick = {
//                            multiplePhotoPickerLauncher.launch(
//                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
//                            )
                            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.ImageSearch,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Galeria")
                        },
                        modifier = Modifier
                            .padding(8.dp),

                    )
                    AssistChip(
                        onClick = {
                            onClickQR()
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Compartir")
                        },
                        modifier = Modifier
                            .padding(8.dp),
                    )
                    /*
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
                            .padding(8.dp),
                    )
                     */

                    AssistChip(
                        onClick = {
                            onDeleteTripButtonClick(routeModel!!.trip!!)
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text("Eliminar")
                        },
                        modifier = Modifier
                            .padding(8.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Origen y destino",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiTransportation,
                        contentDescription = "Auto",
                        modifier = Modifier.size(30.dp),
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    when (routeModel?.trip?.transport) {
                        "driving" -> {
                            Text(
                                text = "Auto",
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            )
                        }

                        "walking" -> {
                            Text(
                                text = "Caminando",
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            )
                        }

                        "bicycling" -> {
                            Text(
                                text = "Bicicleta",
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            )
                        }

                        else -> Text("No hay transporte")
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
                            imageVector = Icons.Default.TripOrigin,
                            contentDescription = "Trip Origin",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 4.dp),
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .padding(start = 8.dp, top = 4.dp),
                            ) {
                                Box(
                                    modifier = Modifier.weight(0.9f),
                                ) {
                                    Text(
                                        text = routeModel!!.trip!!.origin.name,
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                                Box(
                                    modifier = Modifier.weight(0.2f)
                                        .align(Alignment.Top),
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(
                                                shape = RoundedCornerShape(
                                                    15.dp,
                                                    0.dp,
                                                    0.dp,
                                                    15.dp,
                                                ),
                                            )
                                            .background(MaterialTheme.colorScheme.inversePrimary),
                                    ) {
                                        Text(
                                            text = routeModel!!.trip!!.origin.rating.toString(),
                                            modifier = Modifier
                                                .padding(horizontal = 4.dp)
                                                .align(Alignment.CenterVertically),
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Rating",
                                            tint = Color.Yellow,
                                        )
                                    }
                                }
                            }

                            Text(
                                text = routeModel!!.trip!!.origin.address.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

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
                            imageVector = Icons.Default.Flag,
                            contentDescription = "Trip Destination",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 4.dp),
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .padding(start = 8.dp, top = 4.dp),
                            ) {
                                Box(
                                    modifier = Modifier.weight(0.9f),
                                ) {
                                    Text(
                                        text = routeModel!!.trip!!.destination.name,
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                                Box(
                                    modifier = Modifier.weight(0.2f)
                                        .align(Alignment.Top),
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(
                                                shape = RoundedCornerShape(
                                                    15.dp,
                                                    0.dp,
                                                    0.dp,
                                                    15.dp,
                                                ),
                                            )
                                            .background(MaterialTheme.colorScheme.inversePrimary),
                                    ) {
                                        Text(
                                            text = routeModel!!.trip!!.destination.rating.toString(),
                                            modifier = Modifier
                                                .padding(horizontal = 4.dp)
                                                .align(Alignment.CenterVertically),
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Rating",
                                            tint = Color.Yellow,
                                        )
                                    }
                                }
                            }

                            Text(
                                text = routeModel!!.trip!!.destination.address.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Acerca de",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),

            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Trip Destination",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(horizontal = 4.dp),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(4.dp),
                    ) {
                        Text(
                            text = routeModel!!.trip!!.description.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(6.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Imagenes",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        // imagenes
        item {
            LazyRow(
                Modifier.fillMaxWidth(),
            ) {
                items(routeModel!!.trip!!.images ?: emptyList()) { imagen ->
//            Log.i("titi", imagen)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imagen)
                            .size(300, 300)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
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
                    .padding(8.dp),
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
// }
//            }
//
//        }

// }
// }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogShouldShowRationale(
    isShowDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Aceptar")
                }
            },
            title = {
                Text(text = "Aviso de permisos")
            },
            text = {
                Text(
                    text = "Pedimos el permiso de almacenamiento para poder guardar las imagenes del viaje.",
                )
            },
        )
    }
}
