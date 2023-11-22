package com.example.turistaapp.welcome.utils

import java.util.regex.Pattern

fun validateName(name: String): Boolean {
    val pattern = Pattern.compile("^[A-Z][a-zA-Z ]*\$")
    val matcher = pattern.matcher(name)
    return matcher.matches()
}
