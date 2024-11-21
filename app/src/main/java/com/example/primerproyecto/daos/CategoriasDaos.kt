package com.example.primerproyecto.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.primerproyecto.entities.Categorias

@Dao
interface CategoriasDaos {

    @Query("SELECT * FROM categorias")
    fun obtenerCategorias(): List<Categorias>

    @Query("SELECT * FROM categorias WHERE id = :id")
    fun find(id: Int): Categorias


    @Insert
    fun insertarCategoria(categorias: Categorias)

    @Insert
    fun insertarMuchos(vararg categorias: Categorias)

    @Update
    fun modificarCategoria(categorias: Categorias)

    @Delete
    fun eliminarCategoria(categorias: Categorias)
}
