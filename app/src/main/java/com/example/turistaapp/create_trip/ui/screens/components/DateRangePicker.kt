package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.utils.dateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerInput(
    label: String,
//    startDate: Long,
//    endDate: Long,
    startDate: String,
    endDate: String,
    dateRangePickerState: DateRangePickerState,
    showDateRangePicker: Boolean,
    onDismiss: (Boolean) -> Unit,
    onConfirm: (Boolean) -> Unit,
    onClickable: () -> Unit,
) {
//    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
//    formatter.timeZone = TimeZone.getTimeZone("South_America/Argentina")

//    var dateNow by remember {
//        mutableStateOf("$startDate - $endDate")
//    }

    var startDateHere by remember {
        mutableStateOf(startDate)
    }

    var endDateHere by remember {
        mutableStateOf(endDate)
    }

    LaunchedEffect(dateRangePickerState.selectedStartDateMillis){
        startDateHere = dateFormat(dateRangePickerState.selectedStartDateMillis ?: Calendar.getInstance().timeInMillis)
    }

    LaunchedEffect(dateRangePickerState.selectedEndDateMillis){
        endDateHere = dateFormat(dateRangePickerState.selectedEndDateMillis ?: dateRangePickerState.selectedStartDateMillis!!)
    }

    OutlinedTextField(
//        value = "${formatter.format(Date(startDate))} - ${formatter.format(Date(endDate))}",
        value = "$startDate - $endDate",
        onValueChange = { dateRangePickerState.displayMode },
        readOnly = true,
        label = { Text(label) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Timelapse,
                contentDescription = "Date Picker",
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Date Picker",
                modifier = Modifier
                    .size(40.dp),
            )
        },
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            onClickable()
                        }
                    }
                }
            },
        modifier = Modifier
            .fillMaxWidth(),
    )

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
                dateValidator = { timeInMillis ->
//                    val endCalenderDate = Calendar.getInstance()
//                    endCalenderDate.timeInMillis = timeInMillis
//                    endCalenderDate.set(Calendar.DATE, Calendar.DATE + 20)
                    timeInMillis > Calendar.getInstance().timeInMillis //&& timeInMillis < endCalenderDate.timeInMillis//- 172799999 && timeInMillis < endCalenderDate.timeInMillis
                },
                dateFormatter =  DatePickerFormatter(
                    "dd/MM/yyyy",
                    "dd/MM/yyyy",
                    "dd/MM/yyyy"
                ),
                headline = {
                    Text(
                        text = "$startDateHere - $endDateHere",
//                        text = "${dateRangePickerState.selectedStartDateMillis} - ${dateRangePickerState.selectedEndDateMillis}",
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier
                    .height(height = 500.dp), // if I don't set this, dialog's buttons are not appearing
            )
        }
    }
}
