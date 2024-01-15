package com.example.turistaapp.trip_details.ui

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.turistaapp.R
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.domain.models.RouteModel
import com.example.turistaapp.my_trips.ui.screens.components.formatMilisToDateString
import com.example.turistaapp.trip_details.data.AssistChipItem
import com.example.turistaapp.trip_details.ui.components.DialogShouldShowRationale
import com.example.turistaapp.trip_details.ui.components.IconWithText
import com.example.turistaapp.trip_details.ui.components.LocationCard
import com.example.turistaapp.trip_details.ui.components.TextWithComposable
import com.example.turistaapp.trip_details.utils.generateUri

@Composable
fun TripDetails(
    routeModel: RouteModel? = null,
    tripDetailsViewModel: TripDetailsViewModel = hiltViewModel(),
    onClickQR: () -> Unit,
    onClickArrowBack: () -> Unit,
    onDeleteTripButtonClick: (TripModel) -> Unit,
) {

    //Camera
    var uri: Uri? by remember {
        mutableStateOf(null)
    }

    val context = LocalContext.current

    val camera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it && uri?.path?.isNotEmpty() == true) {
                tripDetailsViewModel.updateImages(routeModel!!.trip!!.tripId, uri!!)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) {
        if (it) {
            uri = generateUri(context)
            camera.launch(uri)
        }
    }

//    var selectedImageUris by remember {
//        mutableStateOf<List<String>>(emptyList())
//    }
//    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickMultipleVisualMedia(),
//        onResult = { uris ->
////            selectedImageUris += uris.map { it.toString() }
////            tripDetailsViewModel.updateImages(routeModel!!.trip!!.tripId, selectedImageUris)
//        },
//    )

//    var deniedPermission by remember {
//        mutableStateOf(false)
//    }
//
    var isShowDialog by rememberSaveable {
        mutableStateOf(false)
    }

//    val launcher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission(),
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            multiplePhotoPickerLauncher.launch(
//                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
//            )
//        } else {
//            if (!deniedPermission) {
//                deniedPermission = true
//                isShowDialog = true
//            } else {
////                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//            }
//        }
//    }

    DialogShouldShowRationale(isShowDialog = isShowDialog) {
        isShowDialog = !isShowDialog
    }

    val assistChipItems = listOf(
        AssistChipItem(
            icon = Icons.Default.CameraAlt,
            text = stringResource(R.string.camera),
            onClick = {
//                uri = generateUri(context)
//                camera.launch(uri)
                cameraLauncher.launch(Manifest.permission.CAMERA)
            },
        ),
//        AssistChipItem(
//            icon = Icons.Default.ImageSearch,
//            text = stringResource(R.string.gallery),
//            onClick = { launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE) },
//        ),
        AssistChipItem(
            icon = Icons.Default.Share,
            text = stringResource(R.string.share),
            onClick = { onClickQR() },
        ),
        AssistChipItem(
            icon = Icons.Default.Delete,
            text = stringResource(id = R.string.delete),
            onClick = { onDeleteTripButtonClick(routeModel!!.trip!!) },
        ),
    )

    val transportIcon = when (routeModel?.trip?.transport) {
        "driving" -> Icons.Default.DirectionsCar
        "walking" -> Icons.Default.DirectionsWalk
        "bicycling" -> Icons.Default.DirectionsBike
        else -> Icons.Default.Error
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .offset(y = (-24).dp),
    ) {
        // Trip Name
        item {
            Box(
               Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ){
                Text(
                    text = routeModel!!.trip!!.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterStart),
                )
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterEnd)
                        .clickable {
                            onClickArrowBack()
                        },
                )
            }
        }

        // Trip Dates and Author
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                // Dates
                IconWithText(
                    modifier = Modifier.align(Alignment.CenterStart),
                    icon = Icons.Default.CalendarMonth,
                    text = "${
                        formatMilisToDateString(
                            routeModel?.trip!!.startDate,
                            "dd/MM/yy",
                        )
                    } - ${
                        formatMilisToDateString(
                            routeModel?.trip.endDate,
                            "dd/MM/yy",
                        )
                    }",
                )
                // Author
                IconWithText(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    icon = Icons.Default.PermIdentity,
                    text = stringResource(
                        R.string.for_,
                        routeModel?.trip.author,
                    ),
                )
            }
        }

        // Buttons
        item {
            LazyRow(modifier = Modifier.padding(bottom = 8.dp)) {
                items(assistChipItems) {
                    AssistChip(
                        onClick = it.onClick,
                        label = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.text,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(20.dp),
                            )
                            Spacer(Modifier.size(8.dp))
                            Text(it.text)
                        },
                        modifier = Modifier
                            .padding(end = 16.dp),
                    )
                }
            }
        }

        // Origin and Destination Label with Transport Icon
        item {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            ) {
                Text(
                    text = stringResource(R.string.origin_and_destination),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = routeModel!!.distance,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                    )
                    Icon(
                        imageVector = transportIcon,
                        contentDescription = transportIcon.name,
                        modifier = Modifier
                            .size(40.dp),
                    )
                }
            }
        }

        // Origin and Destination Cards
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
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
                    locationName = routeModel.trip.destination.name,
                    locationRating = routeModel.trip.destination.rating,
                    locationAddress = routeModel.trip.destination.address,
                )
            }
        }

        // About Label
        item {
            TextWithComposable(
                title = stringResource(R.string.description),
                errorMessage = stringResource(R.string.there_is_no_description),
                isShowComposable = routeModel!!.trip!!.description!!.isNotEmpty(),
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp,
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = Icons.Default.Description.name,
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 4.dp),
                        )
                        Text(
                            text = routeModel.trip!!.description.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(8.dp),
                        )
                    }
                }
            }
        }

        // Images Label
        item {
            TextWithComposable(
                title = stringResource(R.string.images),
                errorMessage = stringResource(R.string.no_pictures),
                isShowComposable = !routeModel?.trip?.images.isNullOrEmpty(),
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    items(routeModel!!.trip!!.images ?: emptyList()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it)
                                .size(300, 300)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                        )
                    }
                }
            }
        }

        // Members Label
        item {
            TextWithComposable(
                title = stringResource(R.string.member),
                errorMessage = stringResource(R.string.there_are_no_members),
                isShowComposable = !routeModel?.trip?.members.isNullOrEmpty(),
            ) {
                LazyVerticalGrid(
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
        }
    }
}
