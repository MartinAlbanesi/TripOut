package com.example.turistaapp.welcome.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PagerViewTwo(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Dinos tu nombre para comenzar",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = "Nombre") },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .testTag("nameTextField")
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            isError = isError,
        )
        AnimatedVisibility(
            visible = isError,
            enter = expandVertically(
                animationSpec = tween(500),
                expandFrom = Alignment.Bottom,
            ),
            modifier = Modifier.padding(top = 4.dp),
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "El nombre debe comenzar con mayúscula y no contener números ni caracteres especiales",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 30.dp).testTag("errorText"),
                )
            }
        }
    }
}
