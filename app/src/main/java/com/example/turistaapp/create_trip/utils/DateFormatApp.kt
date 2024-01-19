package com.example.turistaapp.create_trip.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun dateFormat(date: Long) : String {
    return Instant.ofEpochMilli(date)
        .atZone(ZoneId.of("UTC"))
        .toLocalDate().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
        )
}

fun getCurrentDate(): String {
    return LocalDate.now(ZoneId.systemDefault()).format(
        DateTimeFormatter.ofPattern("dd-MM-yyyy")
    )
}