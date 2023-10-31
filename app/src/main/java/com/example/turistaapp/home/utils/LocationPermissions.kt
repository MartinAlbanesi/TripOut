package com.example.turistaapp.home.utils

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager

fun isGPSEnable(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

fun isLocationPermissionsGranted(context: Context): Boolean {
    return context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            context.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}