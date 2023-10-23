package com.example.turistaapp.setting.ui

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetDefaults.SheetPeekHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Size
import com.example.turistaapp.setting.colors.DarkGrayPlusPlus
import com.google.maps.android.compose.GoogleMap
import org.jetbrains.annotations.ApiStatus.Experimental

import java.nio.file.WatchEvent


@Composable
fun TripDetail(
) {
    GoogleMap()
    //    Spacer(modifier = Modifier.size(21.dp))
    BottomScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomScaffold() {
    BottomSheetScaffold(
        sheetContent = {
            BottomScaffoldBody()
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
fun BottomScaffoldBody() {
    Column(
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Arrangement.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.height(800.dp)  // aca es el largo del deslizante
        ) {
         Row (
             modifier = Modifier.fillMaxWidth()
         ) {
             Text(text = "Titulo Viaje", fontSize = 24.sp, color = Color.White)
             Icon(imageVector = Icons.Default.Delete,
                 "" ,
                 tint = Color.White,
                 modifier = Modifier
                     .weight(14f)
                     .fillMaxWidth(100F)
                 // .align(alignment = )

                 //completarlo para que quede al fondo de todo

             )
             }
             Spacer(modifier = Modifier.padding(bottom = 8.dp))
        }
    }

}