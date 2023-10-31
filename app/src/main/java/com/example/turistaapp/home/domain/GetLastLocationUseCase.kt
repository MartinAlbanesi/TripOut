package com.example.turistaapp.home.domain

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.turistaapp.home.utils.isAccessCoarseLocationPermissionsGranted
import com.example.turistaapp.home.utils.isGPSEnable
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class GetLastLocationUseCase @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val context: Context
) {

    @SuppressLint("MissingPermission")
    suspend operator fun invoke(): Location? {
        if(!isGPSEnable(context) || !isAccessCoarseLocationPermissionsGranted(context) ){
            return null
        }

        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(result){}
                    }else{
                        cont.resume(null){}
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it){}
                }
                addOnFailureListener {
                    cont.resume(null){}
                }
                addOnCanceledListener {
                    cont.resume(null){}
                }
            }
        }
    }
}