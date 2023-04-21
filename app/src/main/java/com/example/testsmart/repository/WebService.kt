package com.example.testsmart.repository

import com.example.testsmart.application.Constants
import com.example.testsmart.data.model.PlanetsList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface WebService {

    @GET("api/planets/")
    suspend fun getPlanets(): PlanetsList
}

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}