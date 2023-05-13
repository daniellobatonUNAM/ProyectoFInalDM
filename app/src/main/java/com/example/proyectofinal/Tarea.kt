package com.example.proyectofinal

data class Tarea(
    val id: Long,  // Propiedad id de tipo Long
    val titulo: String,
    val fechaFinalizacion: String,
    val porcentaje: Int
)