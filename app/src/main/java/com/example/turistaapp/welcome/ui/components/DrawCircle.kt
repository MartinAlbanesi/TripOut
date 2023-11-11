package com.example.turistaapp.welcome.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

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
//    Canvas(modifier = modifier) {
//        drawCircle(
//            color = Color.White,
//            radius = 41f,
//            style = Stroke(
//                width = 1.dp.toPx()
//            )
//        )
//    }
}