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
import com.example.turistaapp.BuildConfig
import com.example.turistaapp.R
import com.example.turistaapp.welcome.utils.validateName


@Composable
fun SettingsScreen(
    isDarkTheme: Boolean ,
    userName: String,
    changeName: (String) -> Unit = {},
    changeTheme: () -> Unit,
) {
    //Name
    var isClickedNameChange by remember { mutableStateOf(false) }

    var nameValue by remember { mutableStateOf(userName) }

    var isErrorName by remember { mutableStateOf(false) }

    val nameIsSuccess = stringResource(R.string.name_changed_successfully)

    //Theme
    var checked by rememberSaveable { mutableStateOf(isDarkTheme) }

    //Version
    var isClickedVersion by remember { mutableStateOf(false) }

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
                isClicked = isClickedNameChange,
                onClick = {
                    isClickedNameChange = !isClickedNameChange
                },
            ){
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

            TextWithArrow(
                text = stringResource(R.string.version),
                isClicked = isClickedVersion,
                onClick = {
                    isClickedVersion = !isClickedVersion
                },
            ){
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