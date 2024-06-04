package com.example.plantapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.model.PlantAppRepo
import com.example.plantapp.model.local.dataBase.PlantAppDataBase
import com.example.plantapp.model.local.entities.DetallePlantasLocal
import com.example.plantapp.model.local.entities.ListaPlantasLocal
import kotlinx.coroutines.launch

class PlantAppViewModel(aplicacion: Application): AndroidViewModel(aplicacion) {

    //Conexión repositorio
    private val repo : PlantAppRepo

    //Entidad
    private val detallePlantasLocal = MutableLiveData<DetallePlantasLocal>()

    init {
        val bd = PlantAppDataBase.getDataBase(aplicacion)
        val plantAppDao = bd.getPlantAppDao()
        repo = PlantAppRepo(plantAppDao)

        //Llamar a fetchLista
        viewModelScope.launch {
            repo.fetchPlantas()
        }
    }

        //Listado de elementos
        fun obtenerListaPlantas(): LiveData<List<ListaPlantasLocal>> = repo.listaPlantasRepo


        //Detalle de las plantas
        fun obtenerDetallePlantas(): LiveData<DetallePlantasLocal> = detallePlantasLocal

        fun obtenerDetalleInternet(id:Int) = viewModelScope.launch {
            val detallePlanta = repo.fetchDetallePlanta(id)
            detallePlanta?.let{
                detallePlantasLocal.postValue(it)
            }
        }
    }