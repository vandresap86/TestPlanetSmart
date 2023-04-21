package com.example.testsmart.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Planet(
    val name: String = "",
    val rotation_period: String = "",
    val orbital_period: String = "",
    val diameter: String = "",
    val climate: String = "",
    val gravity: String = "",
    val terrain: String = "",
    val surface_water: String = "",
    val population: String = "",
    val residents: List<String> = listOf(),
    val films: List<String> = listOf(),
    val created: String = "",
    val edited: String = "",
    val url: String = ""
)

data class PlanetsList(val results: List<Planet> = listOf())

