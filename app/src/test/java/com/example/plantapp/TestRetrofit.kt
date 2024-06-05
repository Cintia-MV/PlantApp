package com.example.plantapp

import com.example.plantapp.model.remote.RetrofitPlantApp
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class TestRetrofit {

    // Variable lateinit para MockWebServer
    private lateinit var mockWebServer: MockWebServer

    // Método que se ejecuta antes de cada prueba
    @Before
    fun setUp(){
        // Inicialización el MockWebServer
        mockWebServer = MockWebServer()

        // Inicio del MockWebServer
        mockWebServer.start()
    }


    // Método que se ejecuta después de cada prueba
    @After
    fun tearDown(){
        // Apagar el MockWebServer para liberar los recursos
        mockWebServer.shutdown()
    }

    // Método de prueba para verificar la configuración de Retrofit
    @Test
    fun testRetrofit(){
        // URL base del MockWebServer
        val urlEsperada = mockWebServer.url("/").toString()

        // Configurión Retrofit con la URL del MockWebServer y un convertidor Gson
        val retrofit = Retrofit.Builder()
            .baseUrl(urlEsperada)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Se asigna la instancia de Retrofit configurada a una variable global (simulada aquí con RetrofitPlantApp)
        RetrofitPlantApp.retrofitInstance = retrofit

        // Obtener la instancia de Retrofit desde la variable global
        val retrofitInstance = RetrofitPlantApp.retrofitInstance
        // Verificar que la URL base de la instancia de Retrofit es la misma que la esperada
        assertEquals(urlEsperada, retrofitInstance.baseUrl().toString())
    }

}