package com.example.turistaapp.setting.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor


@Preview
@Composable
fun TripDetail() {
    /* BottomSheetScaffold(
         sheetContent = {
             BottomScaffoldBody(
                 "Titulo viaje",
                 "27/10/2023",
                 "30/10/2023",
                 "Martin",
                 "Name",
                 "San justo",
                 "Unlam",
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
     }*/

    Camera(viewModel = CameraViewModel())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomScaffoldBody(
    titulo: String,
    fechaInicio: String,
    fechaFin: String,
    nombreDelUsuario: String,
    nombre: String,
    valoracionIda: String,
    valoracionFin: String,
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
                                        imageVector = Icons.Default.ImageSearch,
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 5.dp, vertical = 1.dp)
                                            .size(20.dp),
                                    )
                                    Text("Imagen", color = Color.White)
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
                                onClick = {
                                },
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
                                                text = valoracionIda, fontSize = 21.sp,
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
                                                text = valoracionFin,
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera(viewModel: CameraViewModel) {
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.5.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (permissionState.status.isGranted) {
            val context = LocalContext.current

            val cameraController = remember {
                LifecycleCameraController(context)
            }

            val lifecycleCamera = LocalLifecycleOwner.current

            LaunchedEffect(Unit) {
                permissionState.launchPermissionRequest()
            }

            cameraController.bindToLifecycle(lifecycleCamera) //ciclo de vida de la camera , si sale de la app cierra la camara
            AndroidView(factory = { contexts ->
                val previewView = PreviewView(contexts).apply {//uso layaouts con compose
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                }
                previewView.controller = cameraController
                previewView
            })

            Button(
                onClick = {
                    val execut = ContextCompat.getMainExecutor(context)
                    takePicture(cameraController, execut)
                },
                modifier = Modifier
                    .padding(17.dp)
                    .size(90.dp)
                    .align(Alignment.BottomCenter),
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.CameraAlt, contentDescription = "Tomar Foto",
                    modifier = Modifier.size(48.dp)
                )
            }

        } else {
            Text("error")
        }
    }
}

fun takePicture(cameraController: LifecycleCameraController, executor: Executor) {
    val file = File.createTempFile("image", ".jpg") // a la bdd
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(outputDirectory, executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                println(outputFileResults.savedUri)
                Log.i("Titi ayuda", outputFileResults.savedUri.toString())
            }

            override fun onError(exception: ImageCaptureException) {
                Log.i("Titi ayuda", "o me mato")
            }
        }
    )

}

/*
private fun photo(){

    val imageCapture = imageCapture

     val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }

     val outputOptions = ImageCapture.OutputFileOptions
        .Builder(contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues)
        .build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(this),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
            }

            override fun
                    onImageSaved(output: ImageCapture.OutputFileResults){
                val msg = "Photo capture succeeded: ${output.savedUri}"
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, msg)
            }
        }
    )
}*/
