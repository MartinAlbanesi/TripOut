package com.example.turistaapp.welcome

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    var pagerState = rememberPagerState {2}

    val scope = rememberCoroutineScope()

    val buttonText = if (pagerState.currentPage == 0) {
        "Siguiente"
    } else {
        "Empezar"
    }

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
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )

            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                userScrollEnabled = false,
                key = {
                    pagerState.currentPage
                }
            ) { pager ->
                when (pager) {
                    0 -> {
                        ViewOne(stringResource(R.string.Welcome1))
                    }

                    1 -> {
                        ViewTwo(stringResource(R.string.Welcome2))
                    }
                }
            }

            Button(
                onClick = {
                    if(pagerState.currentPage == 0){
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                    if(pagerState.currentPage == 1){
                        Log.i("titi", "WelcomeScreen: ")
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
        fontWeight = FontWeight.Bold,
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


