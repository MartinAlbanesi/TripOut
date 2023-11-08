package com.example.turistaapp.qr_code.domain.models

import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import com.example.turistaapp.create_trip.domain.models.TripModel

data class DataQRModel(
    val tripId: Int = 0,
    val name: String,
    val originID: String,
    val destinationID: String,
    val startDate: String,
    val endDate: String,
    val transport: String,
    val description: String?,
    val author: String,
    val isFavorite: Boolean,
    val isFinished: Boolean,
) {
    override fun toString(): String {
        return "\nTripID: $tripId \nNombre: $name \nOrigen: $originID \nDestino: $destinationID \nFechaInicial: $startDate \nFechaFinal: $endDate \nTrasporte $transport \nDescripcion: $description \nAutor: $author \nFavorito: $isFavorite \nFinalizado: $isFinished"
    }
}

suspend fun DataQRModel.toTripModel(getPlaceDetailsUseCase: GetPlaceDetailsUseCase) = TripModel(
    tripId = this.tripId,
    name = name,
    origin = getPlaceDetailsUseCase(originID)!!,
    destination = getPlaceDetailsUseCase(destinationID)!!,
    startDate = startDate,
    endDate = endDate,
    transport = transport,
    members = null,
    stops = null,
    description = description,
    author = author,
    images = null,
    comments = null,
    isFavorite = isFavorite,
    isFinished = isFinished,
)

fun TripModel.toDataQRModel() = DataQRModel(
    tripId = this.tripId,
    name = name,
    originID = origin.placeID,
    destinationID = destination.placeID,
    startDate = startDate,
    endDate = endDate,
    transport = transport,
    description = description,
    author = author,
    isFavorite = isFavorite,
    isFinished = isFinished,
)
