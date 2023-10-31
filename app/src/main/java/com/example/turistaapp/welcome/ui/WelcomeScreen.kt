package com.example.turistaapp.welcome.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R
import com.example.turistaapp.welcome.ui.components.DrawCircle
import com.example.turistaapp.welcome.ui.components.PagerViewOne
import com.example.turistaapp.welcome.ui.components.PagerViewTwo
import com.example.turistaapp.welcome.utils.validateName
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    onClickSaveName: (String) -> Unit,
) {
    var pagerState = rememberPagerState { 2 }

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


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "TRIP OUT",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 40.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            HorizontalPager(
                modifier = Modifier
                    .weight(5f),
                state = pagerState,
                userScrollEnabled = false,
            ) { pager ->
                when (pager) {
                    0 -> {
                        PagerViewOne(stringResource(R.string.Welcome1))
                    }

                    1 -> {
                        PagerViewTwo(value, isError) {
                            value = it
                        }
                    }
                }
            }

            DrawCircle(
                color = circleFirst,
                Modifier
                    .align(CenterHorizontally)
                    .offset(x = (-20).dp)
            )
            DrawCircle(
                color = circleSecond,
                Modifier
                    .align(CenterHorizontally)
                    .offset(x = (20).dp)
            )

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
                        //TODO: Navigate to main screen, save name, permissions
                        if (validateName(value)) {
                            isError = false
                            onClickSaveName(value)
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


