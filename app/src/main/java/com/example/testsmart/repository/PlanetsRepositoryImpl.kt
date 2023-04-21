package com.example.testsmart.repository

import com.example.testsmart.data.model.PlanetsList
import com.example.testsmart.data.remote.RemotePlanetDataSource

class PlanetsRepositoryImpl(private val dataSorce: RemotePlanetDataSource): PlanetsRepository {
    override suspend fun getPlanets(): PlanetsList = dataSorce.getPlanets()
}