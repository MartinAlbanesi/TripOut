package com.example.turistaapp.map.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.turistaapp.R
import com.example.turistaapp.home.ui.LottiePreview
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapTutorial(
    onClickFinishTutorial: () -> Unit
) {

    val pagerState = rememberPagerState { 3 }

    val scope = rememberCoroutineScope()

    var buttonText by remember {
        mutableStateOf("Siguiente")
    }

    buttonText = if (pagerState.currentPage == 2) "Finalizar" else "Siguiente"

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentAlignment = Alignment.Center
        ) {
            Card {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Tutorial",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                            fontSize = 24.sp
                        )
                        LinearProgressIndicator(
                            progress = pagerState.currentPage.toFloat() / 2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .padding(horizontal = 16.dp)
                                .border(
                                    shape = MaterialTheme.shapes.small,
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.primary
                                ),
                            strokeCap = StrokeCap.Round
                        )
                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .weight(1f),
                    state = pagerState,
                ) {
                    when (it) {
                        0 -> {MapPagerOne()}

                        1 -> {MapPagerTwo()}

                        2 -> {MapPagerThree()}
                    }
                }
                Button(
                    onClick = {
//                        if (pagerState.currentPage == 0) {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1,
                                    animationSpec = tween(500)
                                )
                            }
//                        }
                        if (pagerState.currentPage == 2) {
                            onClickFinishTutorial()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = buttonText)
                }
            }
        }
    }
}

@Composable
private fun MapPagerThree() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Puedes ver tu ubicación tocando el botón de ubicación",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        LottiePreview(title = "", res = R.raw.mylocation) {}
    }
}

@Composable
private fun MapPagerTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Puedes ver los detalles de cada viaje tocando los marcadores rojos", fontSize = 24.sp, textAlign = TextAlign.Center)
        LottiePreview(title = "", res = R.raw.mapmap) {}
    }
}

@Composable
private fun MapPagerOne() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Distintos colores representan distintos tipos de ubicaciones",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        IconWithText(
            modifier = Modifier,
            color = Color.Red,
            text = "Destino",
        )
        IconWithText(
            modifier = Modifier,
            color = Color.Green,
            text = "Origen",
        )
        IconWithText(
            modifier = Modifier,
            color = Color(0xFF3535ea),
            text = "Mi Ubicación",
        )
    }
}

@Composable
private fun IconWithText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .weight(1f)
                .size(48.dp)
        )
        Text(
            text = text,
            modifier = Modifier.weight(2f),
            fontSize = 32.sp
        )
    }
}