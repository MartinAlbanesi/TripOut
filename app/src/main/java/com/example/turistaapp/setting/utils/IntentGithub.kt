package com.example.turistaapp.setting.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun intentGithub(
    context: Context,
    user: String,
) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/$user/"),
            ).setPackage("com.github.android"),
        )
    } catch (e: Exception) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/$user"),
            ),
        )
    }
}
