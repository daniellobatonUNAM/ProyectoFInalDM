package com.example.proyectofinal

import android.content.ContentValues

class ModeloTarea(private val databaseHelper: BDSQLite)  {

    companion object {
        private const val DATABASE_NAME = "tareas.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tareas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_FECHA = "fecha"
        private const val COLUMN_PORCENTAJE = "porcentaje"
    }

    fun insertarTarea(tarea: Tarea): Long {

        val db = databaseHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TITULO, tarea.titulo)
            put(COLUMN_FECHA, tarea.fechaFinalizacion)
            put(COLUMN_PORCENTAJE, tarea.porcentaje)
        }
        return db.insert(TABLE_NAME, null, contentValues)

    }

    fun obtenerTareas(): List<Tarea> {
        val db = databaseHelper.readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_TITULO, COLUMN_FECHA, COLUMN_PORCENTAJE)
        val cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)

        val tareas = mutableListOf<Tarea>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val titulo = getString(getColumnIndexOrThrow(COLUMN_TITULO))
                val fecha = getString(getColumnIndexOrThrow(COLUMN_FECHA))
                val porcentaje = getInt(getColumnIndexOrThrow(COLUMN_PORCENTAJE))

                val tarea = Tarea(null, titulo, fecha,porcentaje)
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
            put(COLUMN_FECHA, tarea.fechaFinalizacion)
            put(COLUMN_PORCENTAJE, tarea.porcentaje)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(tarea.id.toString())

        db.update(TABLE_NAME, contentValues, selection, selectionArgs)
        db.close()
    }

    fun eliminarTarea(tarea: Tarea) {
        val db = databaseHelper.writableDatabase

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(tarea.id.toString())

        db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
    }

    fun obtenerTodas(): MutableList<Tarea> {

        val db = databaseHelper.readableDatabase

        val tareas = mutableListOf<Tarea>()

        db.query(TABLE_NAME, null, null, null, null, null, null)?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA))
                val porcentaje = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PORCENTAJE))

                val tarea = Tarea(id, titulo, fecha, porcentaje)
                tareas.add(tarea)
            }
        }

        return tareas

    }

}