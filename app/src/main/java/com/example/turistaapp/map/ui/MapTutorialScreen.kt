package com.example.turistaapp.map.ui

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.turistaapp.R
import com.example.turistaapp.home.ui.components.LottiePreview
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapTutorial(
    onClickFinishTutorial: () -> Unit,
) {
    val pagerState = rememberPagerState { 4 }

    val scope = rememberCoroutineScope()

    var buttonText by remember {
        mutableStateOf("Siguiente")
    }

    val progressBar by animateFloatAsState(
        targetValue = pagerState.currentPage.toFloat() / 3,
        label = "",
    )

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp.value

    val offsetY by animateFloatAsState(
        targetValue = if (pagerState.currentPage == 0) (screenHeight / 5) else 0f,
        label = "offsetY",
        animationSpec = tween(200),
    )

    buttonText =
        if (pagerState.currentPage == 3) stringResource(R.string.finish) else stringResource(R.string.next)

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            ) {
                Column(
                    Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(R.string.tutorial),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                            .offset(y = offsetY.dp),
                        fontSize = 32.sp,
                    )
                    LinearProgressIndicator(
                        progress = progressBar,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .padding(horizontal = 16.dp)
                            .border(
                                shape = MaterialTheme.shapes.small,
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                        strokeCap = StrokeCap.Round,
                    )
                }
                HorizontalPager(
                    modifier = Modifier
                        .weight(1f),
                    state = pagerState,
                ) {
                    when (it) {
                        0 -> {
                            MapPagerZero()
                        }

                        1 -> {
                            MapPagerOne()
                        }

                        2 -> {
                            MapPagerTwo()
                        }

                        3 -> {
                            MapPagerThree()
                        }
                    }
                }
                Button(
                    onClick = {
                        if (pagerState.currentPage == 3) {
                            onClickFinishTutorial()
                        }
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1,
                                animationSpec = tween(500),
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = stringResource(R.string.tutorial_message_3),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        LottiePreview(title = "", res = R.raw.mylocation) {}
    }
}

@Composable
private fun MapPagerTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = stringResource(R.string.tutorial_message_2),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        LottiePreview(title = "", res = R.raw.mapmap) {}
    }
}

@Composable
private fun MapPagerOne() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = stringResource(R.string.tutorial_message_1),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        IconWithText(
            modifier = Modifier,
            color = Color.Red,
            text = stringResource(R.string.destiny),
        )
        IconWithText(
            modifier = Modifier,
            color = Color.Green,
            text = stringResource(R.string.origen),
        )
        IconWithText(
            modifier = Modifier,
            color = Color(0xFF3535ea),
            text = stringResource(R.string.my_location),
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
                .size(48.dp),
        )
        Text(
            text = text,
            modifier = Modifier.weight(2f),
            fontSize = 32.sp,
        )
    }
}

@Composable
private fun MapPagerZero() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.tutorial_message_0),
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}
