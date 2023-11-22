package com.example.turistaapp.setting.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun intentLinkedin(
    context: Context,
    user: String
) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/$user/")
            ).setPackage("com.linkedin.android")
        )
    } catch (e: Exception) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/$user/")
            )
        )
    }
}