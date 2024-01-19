package com.example.turistaapp.trip_details.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.util.Objects
import java.util.UUID

fun generateUri(
    context: Context
): Uri {
    return FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.turistaapp.provider",
        File.createTempFile(UUID.randomUUID().toString(), ".jpg", context.externalCacheDir)
    )
}