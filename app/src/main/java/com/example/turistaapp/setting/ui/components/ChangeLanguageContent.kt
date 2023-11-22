package com.example.turistaapp.setting.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.setting.data.LanguageApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLanguageContent(
    languages: List<LanguageApp>,
    codeLanguage: String,
    onClick: (String) -> Unit,
) {
    LazyRow {
        items(languages) {
            FilterChip(
                onClick = {
                    onClick(it.code)
                },
                label = {
                    Text(it.name)
                },
                selected = codeLanguage == it.code,
                leadingIcon = if (it.isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else null,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}