package com.example.turistaapp.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R

@Composable
fun ShakeDiscover(
    onClickShakeGame: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClickShakeGame() }
    ) {
        LottiePreview(
            title = "",
            lottieRes = R.raw.world,
            isBackgroundColored = true,
            isBottomBrush = true,
            isClickable = false,
            modifierLottie = Modifier
                .fillMaxWidth()
                .offset(x = 150.dp),
        ) {}

        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0x46000000))
        ) {
            Text(
                text = stringResource(R.string.shake_n_discover),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.your_travel_guide) + "\n" + stringResource(R.string.discover_exciting),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        ElevatedButton(
            onClick = { onClickShakeGame() },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart),
        ) {
            Text(text = stringResource(R.string.pick_shake_trevel))
        }
    }
}