package com.example.turistaapp.setting.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.turistaapp.BuildConfig
import com.example.turistaapp.R
import com.example.turistaapp.setting.data.LanguageApp
import com.example.turistaapp.setting.ui.components.AboutUsContent
import com.example.turistaapp.setting.ui.components.ChangeLanguageContent
import com.example.turistaapp.setting.ui.components.ChangeNameView
import com.example.turistaapp.setting.ui.components.PhotoProfileWithName
import com.example.turistaapp.setting.ui.components.SubTitleSetting
import com.example.turistaapp.setting.ui.components.TextWithArrow
import com.example.turistaapp.setting.ui.components.TextWithSwitch
import com.example.turistaapp.welcome.utils.validateName


@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    userName: String,
    codeLanguage: String = "es",
    changeName: (String) -> Unit = {},
    setCurrentLanguage: (String) -> Unit,
    changeTheme: () -> Unit,
) {
    // Name
    var isClickedNameChange by remember { mutableStateOf(false) }

    var nameValue by remember { mutableStateOf(userName) }

    var isErrorName by remember { mutableStateOf(false) }

    val nameIsSuccess = stringResource(R.string.name_changed_successfully)

    // Language

    var isClickedLanguageChange by remember { mutableStateOf(false) }

    val languages = listOf(
        LanguageApp(stringResource(id = R.string.spanish), "es", codeLanguage == "es"),
        LanguageApp(stringResource(id = R.string.english), "en", codeLanguage == "en"),
    )

    // Theme
    var checked by rememberSaveable { mutableStateOf(isDarkTheme) }

    // About us
    var isClickedAboutUs by remember { mutableStateOf(false) }

    // Version
    var isClickedVersion by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // --------------------------------------------------------Screen

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .fillMaxWidth()
                .height(260.dp)
                .background(MaterialTheme.colorScheme.primary),
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        ) {
            // --------------------------------------------------------Photo and name
            PhotoProfileWithName(name = userName)

            Divider()

            // --------------------------------------------------------Account settings
            SubTitleSetting(text = stringResource(R.string.account_settings))

            // --------------------------------------------------------Change name
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
                                Toast.LENGTH_SHORT,
                            ).show()
                        } else {
                            isErrorName = true
                        }
                    },
                )
            }

            // --------------------------------------------------------Language
            TextWithArrow(
                text = stringResource(R.string.change_language),
                isClicked = isClickedLanguageChange,
                onClick = {
                    isClickedLanguageChange = !isClickedLanguageChange
                },
            ) {
                ChangeLanguageContent(
                    languages = languages,
                    codeLanguage = codeLanguage,
                ) {
                    val appLocale: LocaleListCompat =
                        LocaleListCompat.forLanguageTags(it)
                    AppCompatDelegate.setApplicationLocales(appLocale)
                    setCurrentLanguage(it)
                }
            }

            // --------------------------------------------------------Dark mode
            TextWithSwitch(
                text = stringResource(R.string.dark_mode),
                checked = checked,
                onClickSwitch = {
                    changeTheme()
                    checked = !checked
                },
            )

            Divider()

            // --------------------------------------------------------More
            SubTitleSetting(text = stringResource(R.string.more))

            // --------------------------------------------------------About us
            TextWithArrow(
                text = stringResource(R.string.about_us),
                isClicked = isClickedAboutUs,
                onClick = {
                    isClickedAboutUs = !isClickedAboutUs
                },
            ) {
                AboutUsContent(context = context)
            }

            // --------------------------------------------------------Version
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
