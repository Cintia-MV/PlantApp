package com.example.plantapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lista_plantas_tabla")
data class ListaPlantasLocal (

    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val descripcion: String,
    val imagen:String
)