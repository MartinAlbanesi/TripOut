package com.example.turistaapp.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun ContentView() {
    val pagerState = rememberPagerState { 2 }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PagerContent(pagerState = pagerState)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagerContent(pagerState: PagerState) {

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) { pager ->
        when (pager) {
            0 -> {
                ViewOne()
            }

            1 -> {
                ViewTwo()
            }
        }
    }
}


@Composable
private fun ViewOne() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_name),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
        )
        Text(
            text = "Bienvenido",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun ViewTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_name),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
        )
        Text(
            text = "Trip Out, Aplicación gratuita que nos permite planificar y organizar viajes, pudiendo guardar todos los detalles importantes y permitiendo al usuario realizar múltiples acciones",
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


