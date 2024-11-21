package com.example.primerproyecto.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categorias(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)