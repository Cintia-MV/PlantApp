package com.example.plantapp.model.local.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantapp.model.local.PlantAppDao
import com.example.plantapp.model.local.entities.DetallePlantasLocal
import com.example.plantapp.model.local.entities.ListaPlantasLocal

@Database(entities = [DetallePlantasLocal::class, ListaPlantasLocal::class], version = 1, exportSchema = false)
abstract class PlantAppDataBase : RoomDatabase() {


        abstract fun getPlantAppDao(): PlantAppDao


        companion object{
            @Volatile
            private var INSTANCE: PlantAppDataBase? = null

            fun getDataBase(context: Context): PlantAppDataBase{
                val temInstance = INSTANCE
                if(temInstance!= null){
                    return temInstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlantAppDataBase::class.java,
                        "Plant_app_BD"
                    )
                        .build()
                    INSTANCE= instance
                    return instance
                }
            }
        }
    }
