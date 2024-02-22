package com.example.turistaapp.create_trip.ui.screens.components // ktlint-disable package-name

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel

@Composable
fun PlaceAutocompleteField(
    modifier: Modifier = Modifier,
    modifierMenuList : Modifier = Modifier,
    label: String,
    query: String,
    onQueryChange: (String) -> Unit,
    isDropdownVisible: Boolean,
    onDropdownVisibilityChange: (Boolean) -> Unit,
    predictions: List<PlaceAutocompletePredictionModel>,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClearField: () -> Unit,
    leadingIcon: ImageVector,
    leadingIconOnClick: () -> Unit = {},
//    leadingIcon: @Composable (() -> Unit)? = null,
    onItemClick: (PlaceAutocompletePredictionModel) -> Unit,
    isError: Boolean = false,
    shape: Shape = RoundedCornerShape(8.dp),
    onClickSelectedLocation: () -> Unit = {},
) {
    OutlinedTextField(
        label = { Text(label) },
        singleLine = true,
        maxLines = 1,
        value = query,
        onValueChange = {
            onQueryChange(it)
            if (!isDropdownVisible) onDropdownVisibilityChange(true)

        },
        leadingIcon = {
            IconButton(onClick = { leadingIconOnClick() }) {
                Icon(
                    imageVector = if(isError) Icons.Filled.Error else leadingIcon,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClearField()
                        onDropdownVisibilityChange(false)
                    },
                    modifier = Modifier.background(Color.Transparent),
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }else{
                IconButton(
                    onClick = {
                        onClickSelectedLocation()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
//                    modifier = Modifier.background(Color.Transparent),
                ) {
                    Icon(imageVector = Icons.Default.AddLocationAlt, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        },

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType,
        ),
        isError = isError,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        shape = shape,
    )

    if (isDropdownVisible) {
        Column(
            modifier = modifierMenuList
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.small,
                ),
        ) {
            predictions.forEach { prediction ->
                prediction.structured_formatting?.let {
                    TwoLineListItem(
                        it.main_text ?: "",
                        it.secondary_text ?: "",
                    ) { onItemClick(prediction) }
                    if (predictions.last() != prediction) {
                        Divider(
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp),
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = isError,
        enter = expandVertically(
            animationSpec = tween(500),
            expandFrom = Alignment.Bottom,
        ),
        modifier = Modifier.padding(top = 4.dp),
    ) {
        Text(
            text = "Introduzca una ubicación válida",
            color = MaterialTheme.colorScheme.error,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

// Predictions List Item
@Composable
fun TwoLineListItem(
    mainText: String,
    secondaryText: String,
    onClick: () -> Unit = {},
) {
    Column {
        ListItem(
            headlineContent = {
                Text(
                    text = mainText,
                    fontWeight = FontWeight.Bold,
                )
            },
            supportingContent = {
                Text(
                    text = secondaryText,
                    fontWeight = FontWeight.Light,
                )
            },
            leadingContent = {
                Icon(
                    Icons.Filled.LocationOn,
                    contentDescription = "Localized description",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
        )
    }
}
