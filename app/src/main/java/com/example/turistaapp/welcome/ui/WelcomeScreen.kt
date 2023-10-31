package com.example.turistaapp.welcome.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R
import kotlinx.coroutines.launch
import java.util.regex.Pattern

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
                        ViewOne(stringResource(R.string.Welcome1))
                    }

                    1 -> {
                        ViewTwo(value, isError) {
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


@Composable
private fun ViewOne(text: String) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_name),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(160.dp)
                .align(CenterHorizontally)
                .clip(CircleShape)
        )
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }

}

@Composable
private fun ViewTwo(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dinos tu nombre para comenzar",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = "Nombre") },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            isError = isError
        )
        AnimatedVisibility(
            visible = isError,
            enter = expandVertically(
                animationSpec = tween(500),
                expandFrom = Alignment.Bottom,
            ),
            modifier = Modifier.padding(top = 4.dp),
        ) {
            Text(
                text = "Introduzca un nombre válido",
                color = Color.Red,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
    }
}

@Composable
fun DrawCircle(
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = 40f,
        )
    }
    Canvas(modifier = modifier) {
        drawCircle(
            color = Color.White,
            radius = 41f,
            style = Stroke(
                width = 1.dp.toPx()
            )
        )
    }
}

fun validateName(name: String): Boolean {
    val pattern = Pattern.compile("^[A-Z][a-zA-Z ]*\$")
    val matcher = pattern.matcher(name)
    return matcher.matches()
}


