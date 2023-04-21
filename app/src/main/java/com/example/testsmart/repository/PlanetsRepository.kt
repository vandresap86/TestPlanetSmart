package com.example.testsmart.repository

import com.example.testsmart.data.model.PlanetsList

interface PlanetsRepository {
    suspend fun getPlanets(): PlanetsList
}