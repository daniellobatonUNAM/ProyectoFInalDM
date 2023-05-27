package com.example.proyectofinal

data class Tarea(
    val id: Long?,
    val titulo: String,
    val descripcion: String,
    val fechaFinalizacion: String,
    val fechaInicio: String,
    val deseaRecoratorio: Boolean?,
    val frecuenciaRecordatorio: String?,
    val porcentaje: Int?
)