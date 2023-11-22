package com.example.turistaapp.setting.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R

@Composable
fun ChangeNameView(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    onImeAction: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeAction()
                },
            ),
            trailingIcon = {
                IconButton(onClick = {
                    onImeAction()
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = Icons.Default.Add.name)
                }
            },
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
                    text = stringResource(R.string.error_message_welcome),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                )
            }
        }
    }
}
