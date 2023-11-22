package com.example.turistaapp.setting.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextWithArrow(
    modifier: Modifier = Modifier,
    text: String,
    isClicked: Boolean = false,
    onClick: () -> Unit = {},
    composable: @Composable () -> Unit = {},
) {
    val icon =
        if (!isClicked) Icons.Outlined.KeyboardArrowRight else Icons.Outlined.KeyboardArrowDown

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterStart))
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterEnd),
        )
    }
    AnimatedVisibility(
        visible = isClicked,
        enter = expandVertically(
            animationSpec = tween(500),
        ),
        exit = shrinkVertically(
            animationSpec = tween(500),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        composable()
    }
}
