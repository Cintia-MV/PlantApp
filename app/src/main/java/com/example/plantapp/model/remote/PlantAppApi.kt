package com.example.plantapp.model.remote

import com.example.plantapp.model.remote.internet.DetallePlantasInter
import com.example.plantapp.model.remote.internet.ListaPlantasInter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantAppApi {

    @GET("plantas")
    suspend fun fetchPlantasList(): Response<List<ListaPlantasInter>>

    @GET("plantas/{id}")
    suspend fun fetchDetailPlantas(@Path("id") id:Int): Response<DetallePlantasInter>
}