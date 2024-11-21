package com.example.primerproyecto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.primerproyecto.daos.CategoriasDaos
import com.example.primerproyecto.daos.EjerciciosDaos
import com.example.primerproyecto.entities.Categorias
import com.example.primerproyecto.entities.Ejercicios

@Database(entities = [Categorias::class, Ejercicios::class], version = 2) // Aumenta la versión de 1 a 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriasDaos(): CategoriasDaos
    abstract fun ejerciciosDaos(): EjerciciosDaos
}


object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext, // Asegúrate de usar applicationContext
                AppDatabase::class.java,
                "Guía de ejercicios"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
