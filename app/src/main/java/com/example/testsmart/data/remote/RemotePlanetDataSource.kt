package com.example.testsmart.data.remote

import com.example.testsmart.data.model.PlanetsList
import com.example.testsmart.repository.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemotePlanetDataSource(private val webService: WebService) {
    suspend fun getPlanets(): PlanetsList {
        return webService.getPlanets()
    }
}