package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottiePreview(
    title: String = "",
    lottieRes: Int,
    isBackgroundColored: Boolean = false,
    isClickable: Boolean = false,
    isBottomBrush: Boolean = false,
    isTopBrush: Boolean = false,
    modifierLottie: Modifier = Modifier,
    onClickAnimation: () -> Unit = {},
) {
    val lottie = rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))

    var background by remember {
        mutableStateOf(Color.Transparent)
    }

    var bottomBrush by remember {
        mutableStateOf(Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent)))
    }

    var topBrush by remember {
        mutableStateOf(Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent)))
    }

    if (isBackgroundColored) {
        background = MaterialTheme.colorScheme.onPrimary
    }

    if (isBottomBrush) {
        bottomBrush =
            Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.background))
    }

    if (isTopBrush) {
        topBrush =
            Brush.verticalGradient(listOf(MaterialTheme.colorScheme.background, Color.Transparent))
    }

    val boxClickable: Modifier by remember {
        mutableStateOf(
            if (isClickable) {
                Modifier.clickable { onClickAnimation() }
            } else {
                Modifier
            },
        )
    }

    Box(
        modifier = boxClickable
            .fillMaxWidth()
            .height(200.dp)
            .background(background),
        contentAlignment = Alignment.BottomStart,
    ) {
        Row(
            modifier = Modifier
                .background(brush = topBrush)
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
        ) {
            Spacer(modifier = Modifier.size(8.dp))
        }
        LottieAnimation(
            composition = lottie.value,
            iterations = LottieConstants.IterateForever,
            modifier = modifierLottie
                .fillMaxWidth()
                .align(Alignment.Center),
        )
        if (title.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .background(brush = bottomBrush),
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp, vertical = 12.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(40.dp),
                )
            }
        }
    }
}
