package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    label: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClearField: () -> Unit,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = textValue,
        singleLine = singleLine,
        maxLines = maxLines,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (singleLine) imeAction else ImeAction.Default,
            keyboardType = keyboardType,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        leadingIcon = {
            if (!isError) {
                leadingIcon?.invoke()
            } else {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "Error",
                    tint = Color.Red,
                )
            }
        },
        trailingIcon = {
            if (textValue.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClearField()
                    },
                    modifier = Modifier.background(Color.Transparent),
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        },
        isError = isError,
    )
    // Error message
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
