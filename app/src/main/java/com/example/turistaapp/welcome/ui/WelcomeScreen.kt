package com.example.turistaapp.welcome.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.turistaapp.R
import com.example.turistaapp.welcome.ui.components.PagerViewOne
import com.example.turistaapp.welcome.ui.components.PagerViewTwo
import com.example.turistaapp.welcome.utils.validateName
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    onClickSaveName: (String) -> Unit,
) {

    var showAlertDialog by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp.value

    val pagerState = rememberPagerState { 2 }

    val scope = rememberCoroutineScope()

    val circleFirst by animateColorAsState(
        targetValue = if (pagerState.currentPage == 0) MaterialTheme.colorScheme.primary else Color.Gray,
        label = "circleFirst"
    )

    val circleSecond by animateColorAsState(
        targetValue = if (pagerState.currentPage == 1) MaterialTheme.colorScheme.primary else Color.Gray,
        label = "circleSecond"
    )

    val buttonText = if (pagerState.currentPage == 0) "Siguiente" else "Empezar"

    var value by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }

    val offsetY by animateFloatAsState(
        targetValue = if (pagerState.currentPage == 0) 0f else (screenHeight / 4),
        label = "offsetY",
        animationSpec = tween(1000)
    )

    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(onClick = { onClickSaveName(value) }) {
                    Text(text = "Aceptar")
                }
            },
            title = {
                Text(text = "Aviso de permisos")
            },
            text = {
                Text(
                    text = "Pedimos la ubicación para poder mostrarte los lugares cercanos a ti" +
                            " y para mostrar tu ubicación en el mapa"
                )
            },
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(16.dp))
            AsyncImage(
                model = R.drawable.logo_name,
                contentDescription = null,
                modifier = Modifier
                    .size(240.dp)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = offsetY.dp)
            )

            HorizontalPager(
                modifier = Modifier
                    .weight(5f),
                state = pagerState,
                // userScrollEnabled = false,
            ) { pager ->
                when (pager) {
                    0 -> {
                        PagerViewOne(stringResource(R.string.Welcome1))
                    }

                    1 -> {
                        PagerViewTwo(value, isError) {
                            if (isError) isError = false
                            value = it
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircleBox(color = circleFirst)
                Spacer(modifier = Modifier.size(16.dp))
                CircleBox(color = circleSecond)
            }

            Button(
                onClick = {
                    if (pagerState.currentPage == 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1,
                                animationSpec = tween(500)
                            )
                        }
                    }
                    if (pagerState.currentPage == 1) {
                        if (validateName(value)) {
                            isError = false
//                            onClickSaveName(value)
                            showAlertDialog = true
                        } else {
                            isError = true
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 32.dp)
                    .weight(1f)
            ) {
                Text(text = buttonText)
            }
        }
    }

}

@Composable
fun CircleBox(
    color: Color,
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(color)
    )
}


