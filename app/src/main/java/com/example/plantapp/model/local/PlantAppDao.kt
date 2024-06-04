package com.example.plantapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapp.model.local.entities.DetallePlantasLocal
import com.example.plantapp.model.local.entities.ListaPlantasLocal

@Dao
interface PlantAppDao {

    //Insertar lista de plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarListaPlantas(listaPlantas: List<ListaPlantasLocal>)


    //Seleccionar listado de plantas
    @Query("SELECT * FROM lista_plantas_tabla ORDER BY id ASC")
    fun obtenerListadoPlantas(): LiveData<List<ListaPlantasLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDetallePlanta(planta: DetallePlantasLocal)

    @Query("SELECT * FROM detalle_plantas_tabla WHERE id= :id")
    fun obtenerDetallePlantas(id: Int): LiveData<DetallePlantasLocal>

}