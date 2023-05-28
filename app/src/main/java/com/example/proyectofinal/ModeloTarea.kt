package com.example.proyectofinal

import android.content.ContentValues

class ModeloTarea(private val databaseHelper: BDSQLite)  {

    companion object {
        private const val DATABASE_NAME = "tarea.DB"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tareas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_DESCRIPCION = "descripcion"
        private const val COLUMN_FECHA_FIN = "fecha_fin"
        private const val COLUMN_FECHA_INICIO = "fecha_inicio"
        private const val COLUMN_RECORDATORIO = "recordatorio"
        private const val COLUMN_ESTADO = "estado"
        private const val COLUMN_FRECUENCIA = "frecuencia"
        private const val COLUMN_PORCENTAJE = "porcentaje"
    }

    fun insertarTarea(tarea: Tarea): Long {

        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITULO, tarea.titulo)
            put(COLUMN_DESCRIPCION, tarea.descripcion)
            put(COLUMN_FECHA_FIN, tarea.fechaFinalizacion)
            put(COLUMN_FECHA_INICIO, tarea.fechaInicio)
            put(COLUMN_RECORDATORIO, tarea.deseaRecoratorio)
            put(COLUMN_ESTADO, tarea.estado)
            put(COLUMN_FRECUENCIA, tarea.frecuenciaRecordatorio)
            put(COLUMN_PORCENTAJE, tarea.porcentaje)
        }
        return db.insert(TABLE_NAME, null, contentValues)

    }

    fun obtenerTareas(): List<Tarea> {
        val db = databaseHelper.readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_TITULO, COLUMN_DESCRIPCION,
            COLUMN_FECHA_FIN, COLUMN_FECHA_INICIO, COLUMN_RECORDATORIO, COLUMN_ESTADO,
            COLUMN_FRECUENCIA, COLUMN_PORCENTAJE)
        val cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)

        val tareas = mutableListOf<Tarea>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val titulo = getString(getColumnIndexOrThrow(COLUMN_TITULO))
                val descripcion = getString(getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                val fechaFin = getString(getColumnIndexOrThrow(COLUMN_FECHA_FIN))
                val fechaInicio = getString(getColumnIndexOrThrow(COLUMN_FECHA_INICIO))
                val recordatorioIndex = cursor.getColumnIndexOrThrow(COLUMN_RECORDATORIO)
                val recordatorioInt = cursor.getInt(recordatorioIndex)
                val recordatorio = recordatorioInt != 0
                val estado = getInt(getColumnIndexOrThrow(COLUMN_ESTADO))
                val frecuencia = getString(getColumnIndexOrThrow(COLUMN_FRECUENCIA))
                val porcentaje = getInt(getColumnIndexOrThrow(COLUMN_PORCENTAJE))

                val tarea = Tarea(null, titulo, descripcion, fechaFin, fechaInicio, recordatorio, estado, frecuencia,porcentaje)
                tareas.add(tarea)
            }
        }
        cursor.close()

        return tareas
    }

    fun actualizarTarea(tarea: Tarea) {

        val idK: String = COLUMN_ID

        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITULO, tarea.titulo)
            put(COLUMN_DESCRIPCION, tarea.descripcion)
            put(COLUMN_FECHA_FIN, tarea.fechaFinalizacion)
            put(COLUMN_FECHA_INICIO, tarea.fechaInicio)
            put(COLUMN_RECORDATORIO, tarea.deseaRecoratorio)
            put(COLUMN_ESTADO, tarea.estado)
            put(COLUMN_FRECUENCIA, tarea.frecuenciaRecordatorio)
            put(COLUMN_PORCENTAJE, tarea.porcentaje)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(tarea.id.toString())

        db.update(TABLE_NAME, contentValues, selection, selectionArgs)
        db.close()
    }

    fun eliminarTarea(tarea: Tarea): Boolean {

        val db = databaseHelper.writableDatabase

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(tarea.id.toString())

        val filasEliminadas = db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()

        return filasEliminadas > 0
    }

    fun obtenerTodas(): MutableList<Tarea> {

        val db = databaseHelper.readableDatabase

        val tareas = mutableListOf<Tarea>()

        db.query(TABLE_NAME, null, null, null, null, null, null)?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                val fechaFin = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_FIN))
                val fechaInicio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_INICIO))
                val recordatorioIndex = cursor.getColumnIndexOrThrow(COLUMN_RECORDATORIO)
                val recordatorioInt = cursor.getInt(recordatorioIndex)
                val recordatorio = recordatorioInt != 0
                val estado = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ESTADO))
                val frecuencia = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FRECUENCIA))
                val porcentaje = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PORCENTAJE))

                val tarea = Tarea(id, titulo, descripcion, fechaFin, fechaInicio, recordatorio, estado, frecuencia,porcentaje)
                tareas.add(tarea)
            }
        }

        return tareas

    }

    fun iniciarTarea(tarea: Tarea): Boolean{

        /*
            0: No Iniciado
            1: En progreso
            2: Terminada
         */

        val idK: String = COLUMN_ID

        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_ESTADO, 1)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(tarea.id.toString())

        val rowsAffected = db.update(TABLE_NAME, contentValues, selection, selectionArgs)
        db.close()

        return rowsAffected > 0

    }

}