package com.example.turistaapp.core

import java.io.InputStreamReader

object Helper {

    fun readFileResources(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, Charsets.UTF_8)
        reader.forEachLine {
            builder.append(it)
        }
        return builder.toString()
    }
}