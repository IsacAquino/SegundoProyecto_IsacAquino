package com.example.primerproyecto.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.primerproyecto.entities.Categorias
import com.example.primerproyecto.entities.Ejercicios

@Dao
interface EjerciciosDaos {

    @Query("Select * from ejercicios")
    fun obtenerEjercicios(): List<Ejercicios>

    @Query("Select * from ejercicios where id = :id")
    fun find(id: Int): Ejercicios

    @Query("SELECT * FROM ejercicios WHERE categoriaId = :categoriaId")
    fun getEjerciciosPorCategoria(categoriaId: Int): List<Ejercicios>

    @Insert
    fun insertarEjercicio(ejercicios: Ejercicios)

    @Insert
    fun insertarMuchos(vararg ejercicios: Ejercicios)

    @Update
    fun modificarEjercicio(ejercicios: Ejercicios)

    @Delete
    fun eliminarEjercicio(ejercicios: Ejercicios)

}