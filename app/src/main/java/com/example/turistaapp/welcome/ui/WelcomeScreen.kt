package com.example.turistaapp.welcome.ui

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    var pagerState = rememberPagerState {2}

    val scope = rememberCoroutineScope()

    val circleFirst by animateColorAsState(
        targetValue = if(pagerState.currentPage == 0) MaterialTheme.colorScheme.primary else Color.Gray,
        label = "circleFirst"
    )

    val circleSecond by animateColorAsState(
        targetValue = if(pagerState.currentPage == 1) MaterialTheme.colorScheme.primary else Color.Gray,
        label = "circleSecond"
    )

    val buttonText = if (pagerState.currentPage == 0) "Siguiente" else "Empezar"


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.logo_with_name),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(160.dp)
                    .align(CenterHorizontally)
                    .clip(CircleShape)
            )

            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                userScrollEnabled = false,
            ) { pager ->
                when (pager) {
                    0 -> { ViewOne(stringResource(R.string.Welcome1)) }

                    1 -> { ViewTwo(stringResource(R.string.Welcome2)) }
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
                    if(pagerState.currentPage == 0){
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = tween(500))
                        }
                    }
                    if(pagerState.currentPage == 1){
                        //TODO: Navigate to main screen, save name, permissions
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 32.dp)
            ) {
                Text(text = buttonText)
            }
        }
    }
}


@Composable
private fun ViewOne(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )

}

@Composable
private fun ViewTwo(text: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center
    ) {
        ViewOne(text)
    }
}

@Composable
fun DrawCircle(
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier){
        drawCircle(
            color = color,
            radius = 40f,
        )
    }
    Canvas(modifier = modifier){
        drawCircle(
            color = Color.White,
            radius = 41f,
            style = Stroke(
                width = 1.dp.toPx()
            )
        )
    }
}


