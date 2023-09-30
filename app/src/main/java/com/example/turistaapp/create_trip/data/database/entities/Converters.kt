package com.example.turistaapp.create_trip.data.database.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListToString(lista: MutableList<String>): String {
        return Gson().toJson(lista)
    }

    @TypeConverter
    fun fromStringToList(jsonString: String): MutableList<String> {
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun fromLocationEntityToString(locationEntity: LocationEntity): String {
        return Gson().toJson(locationEntity)
    }

    @TypeConverter
    fun fromStringToLocationEntity(jsonString: String): LocationEntity {
        return Gson().fromJson(jsonString, LocationEntity::class.java)
    }

    @TypeConverter
    fun fromListLocationEntityToString(locationEntityList: MutableList<LocationEntity>): String {
        return Gson().toJson(locationEntityList)
    }

    @TypeConverter
    fun fromStringToListLocationEntity(jsonString: String): MutableList<LocationEntity> {
        val listType = object : TypeToken<MutableList<LocationEntity>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}