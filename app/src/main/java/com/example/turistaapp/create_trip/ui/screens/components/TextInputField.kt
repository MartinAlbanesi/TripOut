package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

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
) {
    OutlinedTextField(
        value = textValue,
        singleLine = singleLine,
        maxLines = maxLines,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (singleLine) imeAction else ImeAction.Default,
            keyboardType = keyboardType,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray,
        ),
        leadingIcon = leadingIcon,
    )
}
