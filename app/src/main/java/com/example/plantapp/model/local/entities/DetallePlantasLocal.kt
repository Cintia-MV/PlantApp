package com.example.plantapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "detalle_plantas_tabla")
data class DetallePlantasLocal(

    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val descripcion: String,
    val imagen: String
)
