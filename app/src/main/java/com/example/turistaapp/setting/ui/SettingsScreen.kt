package com.example.turistaapp.setting.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.turistaapp.BuildConfig
import com.example.turistaapp.R
import com.example.turistaapp.setting.domain.LanguageApp
import com.example.turistaapp.welcome.utils.validateName


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    userName: String,
    codeLanguage: String = "es",
    changeName: (String) -> Unit = {},
    setCurrentLanguage: (String) -> Unit,
    changeTheme: () -> Unit,
) {
    //Name
    var isClickedNameChange by remember { mutableStateOf(false) }

    var nameValue by remember { mutableStateOf(userName) }

    var isErrorName by remember { mutableStateOf(false) }

    val nameIsSuccess = stringResource(R.string.name_changed_successfully)

    //Language

    var isClickedLanguageChange by remember { mutableStateOf(false) }

    val languages = listOf(
        LanguageApp(stringResource(id = R.string.spanish), "es", codeLanguage == "es"),
        LanguageApp(stringResource(id = R.string.english), "en", codeLanguage == "en"),
    )

    //Theme
    var checked by rememberSaveable { mutableStateOf(isDarkTheme) }

    //About us
    var isClickedAboutUs by remember { mutableStateOf(false) }

    //Version
    var isClickedVersion by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
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
                isClicked = isClickedNameChange,
                onClick = {
                    isClickedNameChange = !isClickedNameChange
                },
            ) {
                ChangeNameView(
                    value = nameValue,
                    isError = isErrorName,
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
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            isErrorName = true
                        }
                    }
                )
            }

            TextWithArrow(
                text = stringResource(R.string.change_language),
                isClicked = isClickedLanguageChange,
                onClick = {
                    isClickedLanguageChange = !isClickedLanguageChange
                }
            ) {
                LazyRow {
                    items(languages) {
                        FilterChip(
                            onClick = {
                                val appLocale: LocaleListCompat =
                                    LocaleListCompat.forLanguageTags(it.code)
                                AppCompatDelegate.setApplicationLocales(appLocale)
                                setCurrentLanguage(it.code)
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

            TextWithArrow(
                text = stringResource(R.string.about_us),
                isClicked = isClickedAboutUs,
                onClick = {
                    isClickedAboutUs = !isClickedAboutUs
                },
            ) {
                LazyColumn {
                    item {
                        Text(
                            text = "Somos un pequeño grupo de estudiantes apasionados por el desarrollo de aplicaciones móviles modernas e intuitivas\n" +
                                    "En TripOut te ofrecemos la posibilidad de planificar tus viajes de la manera mas organizada posible, brindandote todas las herramientas necesarias para cumplir con tus objetivos.\n" +
                                    "Prepárate para embarcarte en un viaje único con TripOut. ¡Tu próxima aventura comienza aquí!\n" +
                                    "Equipo de TripOut" +
                                    "\n\n Contactanos:",
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )
                        TextButton(onClick = {
                            intentLinkedin(context, "martin-albanesi")
                        }) {
                            Text(text = "Albanesi Martin")
                        }
                        TextButton(onClick = {
                            intentLinkedin(context, "gabrielgomezgg")
                        }) {
                            Text(text = "Gomez Gabriel")
                        }
                        TextButton(onClick = {
                            intentLinkedin(context, "ariel-eduardo-nappio-7840071a4")
                        }) {
                            Text(text = "Nappio Ariel")
                        }
                    }
                }
            }

            TextWithArrow(
                text = stringResource(R.string.version),
                isClicked = isClickedVersion,
                onClick = {
                    isClickedVersion = !isClickedVersion
                },
            ) {
                Text(
                    text = BuildConfig.VERSION_NAME,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        }
    }
}

fun intentLinkedin(
    context: Context,
    user: String
) {
    try{
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/$user/")).setPackage("com.linkedin.android"))
    }catch (e: Exception){
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/$user/")))
    }
}

@Composable
fun ChangeNameView(
    value: String,
    isError: Boolean,
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
            trailingIcon = {
                IconButton(onClick = {
                    onImeAction()
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = Icons.Default.Add.name)
                }
            }
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
    val icon =
        if (!isClicked) Icons.Outlined.KeyboardArrowRight else Icons.Outlined.KeyboardArrowDown

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
    ) {
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