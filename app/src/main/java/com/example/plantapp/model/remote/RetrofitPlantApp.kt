package com.example.plantapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPlantApp {

    companion object{
        private const val BASE_URL = "https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var retrofitInstance: Retrofit

        fun getRetrofit(): PlantAppApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PlantAppApi::class.java)
        }
    }
}