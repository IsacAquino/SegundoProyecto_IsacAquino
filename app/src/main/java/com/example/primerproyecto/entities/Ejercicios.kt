package com.example.primerproyecto.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ejercicios",
    foreignKeys = [ForeignKey(
        entity = Categorias::class,
        parentColumns = ["id"],
        childColumns = ["categoriaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Ejercicios(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val instrucciones: String,
    val categoriaId: Int  // Asegúrate de que este campo coincida con el nombre en el ForeignKey
)
