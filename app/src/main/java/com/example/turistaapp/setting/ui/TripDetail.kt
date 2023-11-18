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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.turistaapp.R
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
        // Trip Name
        item {
            Text(
                text = routeModel!!.trip!!.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }

        // Trip Dates and Author
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
            ) {
                // Dates
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
                            textAlign = TextAlign.Center,
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

                // Author
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
                            text = stringResource(
                                R.string.for_,
                                routeModel?.trip?.author ?: stringResource(R.string.anonymous)
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }

        // Buttons
        item {
            LazyRow {
                item {
                    AssistChip(
                        onClick = {
                            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.ImageSearch,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
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
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text(stringResource(id = R.string.share))
                        },
                        modifier = Modifier
                            .padding(8.dp),
                    )

                    AssistChip(
                        onClick = {
                            onDeleteTripButtonClick(routeModel!!.trip!!)
                        },
                        label = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 1.dp)
                                    .size(20.dp),
                            )
                            Text(stringResource(id = R.string.delete))
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
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }

        // Origin and Destination Label with Transport Icon
        item {
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
                        text = stringResource(R.string.origin_and_destination),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    when (routeModel?.trip?.transport) {
                        "driving" -> {
                            Icon(
                                imageVector = Icons.Default.DirectionsCar,
                                contentDescription = "Driving",
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }

                        "walking" -> {
                            Icon(
                                imageVector = Icons.Default.DirectionsWalk,
                                contentDescription = "Walking",
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }

                        "bicycling" -> {
                            Icon(
                                imageVector = Icons.Default.DirectionsBike,
                                contentDescription = "Biking",
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }

                        else -> Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "No transport",
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }

        // Origin and Destination Cards
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                // Origin
                LocationCard(
                    icon = Icons.Default.TripOrigin,
                    locationName = routeModel!!.trip!!.origin.name,
                    locationRating = routeModel.trip!!.origin.rating,
                    locationAddress = routeModel.trip.origin.address,
                )

                Spacer(modifier = Modifier.size(16.dp))

                // Destination
                LocationCard(
                    icon = Icons.Default.Flag,
                    locationName = routeModel!!.trip!!.destination.name,
                    locationRating = routeModel.trip.destination.rating,
                    locationAddress = routeModel.trip.destination.address,
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }

        // About Label
        item {
            Text(
                text = stringResource(R.string.about),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }

        // About Card
        item {
            if (routeModel!!.trip!!.description!!.isNotEmpty()) {
                ElevatedCard(
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
            } else {
                PlaceholderElevatedCard(
                    text = stringResource(R.string.there_is_no_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
        }

        // Images Label
        item {
            Text(
                text = stringResource(R.string.images),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.size(10.dp))
        }

        // Photo Gallery
        item {
            if (!routeModel?.trip?.images.isNullOrEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                15.dp,
                                15.dp,
                                15.dp,
                                15.dp,
                            ),
                        )
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    items(routeModel!!.trip!!.images ?: emptyList()) { imagen ->
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
            } else {
                PlaceholderElevatedCard(
                    text = stringResource(R.string.no_pictures),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
        }

        // Members Label
        item {
            Text(
                text = stringResource(R.string.member),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
        }

        if (!routeModel?.trip?.members.isNullOrEmpty()) {
            item {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(10.dp),
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .heightIn(max = 170.dp),
                ) {
                    items(routeModel!!.trip!!.members ?: emptyList()) { chipValue ->
                        AssistChip(
                            onClick = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.PermIdentity,
                                    contentDescription = "Member Chip",
                                    modifier = Modifier
                                        .size(20.dp),
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                leadingIconContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            label = {
                                Text(
                                    text = chipValue,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(vertical = 4.dp),
                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = 6.dp),
                        )
                    }
                }
            }
        } else {
            item {
                PlaceholderElevatedCard(
                    text = stringResource(R.string.there_are_no_members),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                )
            }
        }

        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun PlaceholderElevatedCard(
    text: String,
    modifier: Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

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
                            text = locationName,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.2f)
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
                                text = locationRating.toString(),
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
                    text = locationAddress ?: stringResource(R.string.there_is_no_direction),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }
        }
    }
}

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
                    Text(text = stringResource(R.string.accept))
                }
            },
            title = {
                Text(text = stringResource(id = R.string.permission_required))
            },
            text = {
                Text(
                    text = stringResource(R.string.storage_permission_message),
                )
            },
        )
    }
}
