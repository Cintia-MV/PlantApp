package com.example.plantapp

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.example.plantapp.model.local.PlantAppDao
import com.example.plantapp.model.local.dataBase.PlantAppDataBase
import com.example.plantapp.model.local.entities.ListaPlantasLocal
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestInstrumentalDao {

    //Variables de prueba
    private lateinit var db: PlantAppDataBase
    private lateinit var plantAppDao: PlantAppDao


    @Before
    fun setUp(){
        //Inicialización de la base de datos y el DAO
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, PlantAppDataBase::class.java).build()
        plantAppDao = db.getPlantAppDao()
    }


    @After
    fun tearDown(){
        //Cierre de la base de datos
        db.close()
    }


    @Test
    fun testCrudOperacion() = runBlocking {
        //Creación datos de prueba
        val listaPlantas = listOf(
            ListaPlantasLocal(1, "Rosa", "Flor", "Flor rosa", "img1"),
            ListaPlantasLocal(2, "Tomate", "Fruta", "Fruta roja", "img2")
        )

        //Insertar los datos de prueba en la base de datos
        plantAppDao.insertarListaPlantas(listaPlantas)

        //Observar LiveData en el hilo principal
        runOnUiThread{
            //Obtener LiveData de todas las plantas
            val todasLasPlantasLD = plantAppDao.obtenerListadoPlantas()

            //Crear el observador de la lista de plantas
            val observadorPlantas = Observer<List<ListaPlantasLocal>> {listadoPlantas ->
                ViewMatchers.assertThat(listadoPlantas, CoreMatchers.not(emptyList()))

                assertEquals(2, listadoPlantas.size)
            }

            //Observar las plantas
            todasLasPlantasLD.observeForever(observadorPlantas)
            // Quitar el observador después de realizar las aserciones
            todasLasPlantasLD.removeObserver(observadorPlantas)
        }


    }


}