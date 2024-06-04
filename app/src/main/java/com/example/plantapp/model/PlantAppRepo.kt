package com.example.plantapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.plantapp.model.local.PlantAppDao
import com.example.plantapp.model.local.entities.DetallePlantasLocal
import com.example.plantapp.model.local.entities.ListaPlantasLocal
import com.example.plantapp.model.remote.RetrofitPlantApp


class PlantAppRepo (private val plantAppDao: PlantAppDao) {
    //Retrofit
    private val networkService = RetrofitPlantApp.getRetrofit()

    //Lista de plantas DAO
    val listaPlantasRepo = plantAppDao.obtenerListadoPlantas()

    //Detalle de una planta
    val detallePlantasLocal = MutableLiveData<DetallePlantasLocal>()


    //Listado
    val listaPlantasLocal = MutableLiveData<ListaPlantasLocal>()


    suspend fun fetchPlantas() {
        val service = kotlin.runCatching { networkService.fetchPlantasList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Cursos", it.toString())
                    plantAppDao.insertarListaPlantas(plantasDeInternet(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.d("ERROR", "${it.message}")
            }

        }
    }

        suspend fun fetchDetallePlanta(id: Int): DetallePlantasLocal?{
            val service = kotlin.runCatching { networkService.fetchDetailPlantas(id)}

            return service.getOrNull()?.body()?.let{planta ->
                val detallePlantasEntity = detalleDeInternet(planta)
                plantAppDao.insertarDetallePlanta(detallePlantasEntity)
                detallePlantasEntity

            }
        }


}