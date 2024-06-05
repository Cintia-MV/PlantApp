package com.example.plantapp

import com.example.plantapp.model.local.entities.ListaPlantasLocal
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestListaPlantaEntidad {

    // Variable lateinit para la entidad
    private lateinit var listaPlantas : ListaPlantasLocal

    // Función que se ejecuta antes de cada prueba
    @Before
    fun setUp(){
        // Entidad inicializada  con valores de prueba
        listaPlantas = ListaPlantasLocal(
            id = 1,
            imagen = "img1",
            nombre = "Hortensia",
            tipo = "Flor",
            descripcion = "Es una flor"
        )
    }

    // Función que se ejecuta después de cada prueba
    @After
    fun tearDown(){

    }


    // Función de prueba para verificar el constructor de la entidad
    @Test
    // Verificar que los valores asignados en el constructor sean correctos
    fun testListaPlantas(){
        assert(listaPlantas.id == 1)
        assert(listaPlantas.imagen == "img1")
        assert(listaPlantas.nombre== "Hortensia")
        assert(listaPlantas.tipo== "Flor")
        assert(listaPlantas.descripcion == "Es una flor")

    }



}