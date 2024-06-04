package com.example.plantapp.model

import com.example.plantapp.model.local.entities.DetallePlantasLocal
import com.example.plantapp.model.local.entities.ListaPlantasLocal
import com.example.plantapp.model.remote.internet.DetallePlantasInter
import com.example.plantapp.model.remote.internet.ListaPlantasInter

fun plantasDeInternet(listaPlantas: List<ListaPlantasInter>): List<ListaPlantasLocal> {

    return listaPlantas.map {

        ListaPlantasLocal(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            descripcion = it.descripcion,
            imagen = it.imagen

        )
    }

}

fun detalleDeInternet(detalle: DetallePlantasInter): DetallePlantasLocal{
    return DetallePlantasLocal(
        id = detalle.id,
        nombre = detalle.nombre,
        tipo = detalle.tipo,
        descripcion = detalle.descripcion,
        imagen = detalle.imagen

    )
}