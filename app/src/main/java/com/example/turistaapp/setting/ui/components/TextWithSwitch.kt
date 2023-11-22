package com.example.turistaapp.setting.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextWithSwitch(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onClickSwitch: (Boolean) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterStart))

        Switch(
            checked = checked,
            onCheckedChange = {
                onClickSwitch(it)
            },
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}
