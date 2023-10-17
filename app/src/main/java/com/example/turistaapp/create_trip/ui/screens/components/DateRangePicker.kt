package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerInput(
    label: String,
    startDate: Long,
    endDate: Long,
    dateRangePickerState: DateRangePickerState,
    showDateRangePicker: Boolean,
    onDismiss: (Boolean) -> Unit,
    onConfirm: (Boolean) -> Unit,
    onClickable: () -> Unit,
) {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    formatter.timeZone = TimeZone.getTimeZone("South_America/Argentina")

    if (showDateRangePicker) {
        DatePickerDialog(
            onDismissRequest = {
                onDismiss(false)
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm(false)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss(false)
                }) {
                    Text(text = "Cancel")
                }
            },
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                modifier = Modifier.height(height = 500.dp), // if I don't set this, dialog's buttons are not appearing
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clickable {
                onClickable()
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = "${formatter.format(Date(startDate))} - ${formatter.format(Date(endDate))}",
                onValueChange = { dateRangePickerState.displayMode },
                readOnly = true,
                label = { Text(label) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                onClickable()
                            },
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}
