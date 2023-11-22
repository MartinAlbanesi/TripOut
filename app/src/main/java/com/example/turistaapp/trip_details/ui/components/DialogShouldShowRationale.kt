package com.example.turistaapp.trip_details.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.turistaapp.R

@Composable
fun DialogShouldShowRationale(
    isShowDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(R.string.accept))
                }
            },
            title = {
                Text(text = stringResource(id = R.string.permission_required))
            },
            text = {
                Text(
                    text = stringResource(R.string.storage_permission_message),
                )
            },
        )
    }
}
