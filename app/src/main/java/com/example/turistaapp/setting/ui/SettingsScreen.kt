package com.example.turistaapp.setting.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turistaapp.R
import com.example.turistaapp.welcome.utils.validateName


@Composable
fun SettingsScreen(
    isDarkTheme: Boolean ,
    userName: String,
    changeName: (String) -> Unit = {},
    changeTheme: () -> Unit,
) {

    var checked by rememberSaveable { mutableStateOf(isDarkTheme) }

    var isChangeName by remember { mutableStateOf(false) }

    var nameValue by remember { mutableStateOf(userName) }

    var isErrorName by remember { mutableStateOf(false) }

    val nameIsSuccess = stringResource(R.string.name_changed_successfully)

    val context = LocalContext.current


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .fillMaxWidth()
                .height(260.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        ) {
            PhotoProfileWithName(name = userName)

            Divider()

            //Configuraciones de la cuenta
            SubTitleSetting(text = stringResource(R.string.account_settings))

            TextWithArrow(
                text = stringResource(R.string.rename),
                isClicked = isChangeName,
                onClick = {
                    isChangeName = !isChangeName
                },
            ){
                ChangeNameView(
                    value = nameValue,
                    isError = isErrorName,
                    userName = nameValue,
                    onValueChange = {
                        nameValue = it
                        if (isErrorName) isErrorName = false
                    },
                    onImeAction = {
                        if (validateName(nameValue)) {
                            isErrorName = false

                            changeName(nameValue)

                            Toast.makeText(
                                context,
                                nameIsSuccess,
                                Toast.LENGTH_SHORT).show()

                        } else {
                            isErrorName = true
                        }
                    }
                )
            }

            TextWithArrow(text = stringResource(R.string.change_language))

            TextWithSwitch(
                text = stringResource(R.string.dark_mode),
                checked = checked,
                onClickSwitch = {
                    changeTheme()
                    checked = !checked
                },
            )

            Divider()

            //More
            SubTitleSetting(text = stringResource(R.string.more))

            TextWithArrow(text = stringResource(R.string.about_us))

            TextWithArrow(text = stringResource(R.string.version))
        }
    }
}

@Composable
fun ChangeNameView(
    value: String,
    isError: Boolean,
    userName: String,
    onValueChange: (String) -> Unit,
    onImeAction: () -> Unit
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
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onImeAction()
                }
            ),
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
                        .padding(horizontal = 30.dp)
                )
            }
        }
    }
}
@Composable
fun TextWithSwitch(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onClickSwitch: (Boolean) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterStart))

        Switch(
            checked = checked,
            onCheckedChange = {
                onClickSwitch(it)
            },
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}

@Composable
fun TextWithArrow(
    modifier: Modifier = Modifier,
    text: String,
    isClicked: Boolean = false,
    onClick: () -> Unit = {},
    composable: @Composable () -> Unit = {},
) {
    val icon = if (!isClicked) Icons.Outlined.KeyboardArrowRight else Icons.Outlined.KeyboardArrowDown

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterStart))
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterEnd)
        )
    }
    AnimatedVisibility(
        visible = isClicked,
        enter = expandVertically(
            animationSpec = tween(500),
        ),
        exit = shrinkVertically(
            animationSpec = tween(500),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        composable()
    }
}

@Composable
fun SubTitleSetting(
    text: String,
) {
    Text(
        text = text,
        modifier = Modifier.padding(16.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.outline,
    )
}

@Composable
fun PhotoProfileWithName(
    modifier: Modifier = Modifier,
    name: String,
    icon: ImageVector = Icons.Default.Person,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

/*
@Composable
fun SettingsScreen(changeTheme: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
    ) {
        SettingAppearance { changeTheme() }
        Spacer(modifier = Modifier.size(16.dp))
        About()
        Box(
            contentAlignment = BottomCenter,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            SettingVersion()
        }
    }
}

@Composable
fun SettingAppearance(changeTheme: () -> Unit) {
    var checked by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Text(
            text = "Aparencia",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (!checked) {
                Text(
                    "Modo oscuro",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(5f)
                        .align(CenterVertically),
                )
            } else {
                Text(
                    "Modo claro",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(5f)
                        .align(CenterVertically),
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = {
                    changeTheme()
                    checked = it
                },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun SettingVersion() {
    Text(
        "Version 1.1",
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun About() {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
        ) {
            Text("Conoce nuestro equipo", fontSize = 24.sp)
            Icon(
                imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }

        if (isExpanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(),
            ) {
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp,
                        ),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        modifier = Modifier
                            .fillParentMaxHeight(0.98f),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Green)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Red)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight(0.33f)
                                .background(Color.Blue)
//                                .combinedClickable(
//                                    onClick = { },
//                                    onLongClick = { },
//                                ),
                        ) {
                            Text(
                                text = "TripOut",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                            )
                        }
                    }
                }
                /*
                item {
                    Text(
                        text = "Somos un pequeño grupo de estudiantes apasionados por el desarrollo de aplicaciones móviles modernas e intuitivas\n" +
                            "En TripOut te ofrecemos la posibilidad de planificar tus viajes de la manera mas organizada posible, brindandote todas las herramientas necesarias para cumplir con tus objetivos.\n" +
                            "Prepárate para embarcarte en un viaje único con TripOut. ¡Tu próxima aventura comienza aquí!\n" +
                            "Martín, Gabriel y Ariel\n" +
                            "Equipo de TripOut",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                 */
            }
        }
    }
}
 */
