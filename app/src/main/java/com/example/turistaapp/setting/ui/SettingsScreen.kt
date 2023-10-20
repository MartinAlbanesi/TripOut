package com.example.turistaapp.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.ui.theme.TuristaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(darkTheme:Boolean , changeTheme : () -> Unit ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
//     verticalAlignment = Alignment.CenterVertically

    ) {
        SettingUser()
        Spacer(modifier = Modifier.size(21.dp))
        //Divider( thickness = 2.dp , modifier = Modifier.padding(4.dp))
        SettingAppearance(darkTheme) { changeTheme() }
        Spacer(modifier = Modifier.size(21.dp))
        SettingMore()
        Spacer(modifier = Modifier.size(264.dp))
        SettingVersion()

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
            //.clip(Shape())
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Text(text = "Usuario", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.size(6.dp))

        Text("Nombre de usuario", fontSize = 16.sp)
        Spacer(modifier = Modifier.size(6.dp))
        TextField(value = nameRemember,
            onValueChange = { nameRemember = it },
            modifier = Modifier.fillMaxWidth() ,
            singleLine = true
            )
    }
}

@Composable
fun SettingAppearance(darkTheme: Boolean, changeTheme: () -> Unit ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Text(text = "Aparencia", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                "modo oscuro", fontSize = 20.sp, color = Color.Black, modifier = Modifier
                    .weight(5f)
                    .align(CenterVertically)
            )
            var checked by rememberSaveable { mutableStateOf(true) }

           Switch(
                checked = checked,
                onCheckedChange = {
                    changeTheme()
                }
                , modifier = Modifier
                    .weight(1f)

            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text("cambiar idioma", fontSize = 20.sp, color = Color.Black, modifier = Modifier)
    }
}
@Composable
fun SettingMore() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Text(text = "MAS", fontSize = 24.sp, color = Color.Black.copy(alpha = 0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Informacion legal",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            )
            IconButton(onClick = { /*TODO*/}) {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = ""
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
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center

    )

}
/*

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

/*
@Composable
fun DropDownMenuExample(){
val options = listOf("español","ingles","portuges")
var expanded by remember { mutableListOf() }

}*/


 */