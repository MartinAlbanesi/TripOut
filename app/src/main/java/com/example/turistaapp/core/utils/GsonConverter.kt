package com.example.turistaapp.core.utils

import com.google.gson.Gson

object GsonConverter {

    private val gson = Gson()

    fun <T> toJson(data: T): String {
        return gson.toJson(data)
    }

    fun <T> fromJson(data: String, clazz: Class<T>): T {
        return gson.fromJson(data, clazz)
    }
}
