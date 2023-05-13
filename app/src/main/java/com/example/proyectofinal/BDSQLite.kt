package com.example.proyectofinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDSQLite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    companion object {
        private const val DATABASE_NAME = "tareas.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tareas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_FECHA = "fecha"
        private const val COLUMN_PORCENTAJE = "porcentaje"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITULO TEXT, " +
                "$COLUMN_FECHA TEXT, " +
                "$COLUMN_PORCENTAJE INTEGER)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}