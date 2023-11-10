package com.example.turistaapp.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(darkTheme: Boolean = false, changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
//     verticalAlignment = Alignment.CenterVertically

    ) {
//        SettingUser()
//        Spacer(modifier = Modifier.size(21.dp))
        // Divider( thickness = 2.dp , modifier = Modifier.padding(4.dp))
        SettingAppearance(darkTheme) { changeTheme() }
//        Spacer(modifier = Modifier.size(21.dp))
//        SettingMore("MAS")
//        Spacer(modifier = Modifier.size(264.dp))
//        SettingVersion()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingUser() {
    var nameRemember by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            // .clip(Shape())
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(text = "Usuario", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.size(6.dp))

        Text("Nombre de usuario", fontSize = 16.sp)
        Spacer(modifier = Modifier.size(6.dp))
        TextField(
            value = nameRemember,
            onValueChange = { nameRemember = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
    }
}

@Composable
fun SettingAppearance(darkTheme: Boolean, changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(text = "Aparencia", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Row(
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(
                "modo oscuro",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(5f)
                    .align(CenterVertically),
            )
            var checked by rememberSaveable { mutableStateOf(true) }

            Switch(
                checked = checked,
                onCheckedChange = {
                    changeTheme()
                    checked = it
                },
                modifier = Modifier.weight(1f),
            )
        }
//        Spacer(modifier = Modifier.size(6.dp))
//        Row  {
//            Text(
//                "cambiar idioma",
//                 fontSize = 20.sp,
//                 color = Color.Black,
//                modifier = Modifier
//                    .weight(3f)
//                    .align(CenterVertically)
//            )
//            val a: SettingViewModel = viewModel()
//
//            // val b =   listOf<String>("español", "ingles","portuges")
//            DropDownMenu(lista = a.listLanguage)
//        }
    }
}

@Composable
fun SettingMore(prueba: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(text = prueba, fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Informacion legal",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically),
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = "",
                    // modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun SettingVersion() {
    Text(
        "Version 1,4",
        fontSize = 20.sp,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
} /*

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun idiomas() {
    val items = listOf("Español","Ingles","Frances","Japon")

    var isExpanden by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items) }

    ExposedDropdownMenuBox(expanded = isExpanden,
        onExpandedChange ={isExpanden = it}) {
        DropdownMenu(expanded = isExpanden ,
            onDismissRequest = {isExpanden = false} ,
            modifier = Modifier.fillMaxWidth()
        ){
            items.forEach{ itt ->
                DropdownMenuItem(
                    text = {Text(text = itt)},
                    onClick = {
                        selectedItem = items
                        isExpanden = true
                    })
            }
        }
    }
}
*/

// traer del viewModel ()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(lista: List<String>) {
    // val options = listOf("Español", "Ingles")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(lista[0]) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier,

    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .widthIn(min = 30.dp)
                .fillMaxWidth(0.4f),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            // label = { Text(" ") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),

        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .widthIn(min = 50.dp)
                .fillMaxWidth(0.5f),
        ) {
            lista.forEach { Ite ->
                DropdownMenuItem(
                    text = { Text(Ite) },
                    onClick = {
                        expanded = false
                        selectedOptionText = Ite
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            } /*
                        DropdownMenuItem(
                            text = { Text("") },
                            onClick = {
                                expanded = false
                                Log.i("a", "cualquuier cosa xD")
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
            */
            // }
        }
    }
}
