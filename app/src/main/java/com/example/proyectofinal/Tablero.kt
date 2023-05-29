package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class Tablero : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var conexion: BDSQLite
    private lateinit var modelo: ModeloTarea

    private lateinit var listaPendientes: TextView
    private lateinit var listaEnProgreso: TextView
    private lateinit var listaTerminadas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tablero)

        btnBack = findViewById(R.id.btnBackBoard)
        backMain()

        conexion = BDSQLite(this)
        modelo = ModeloTarea(conexion)

        listaPendientes = findViewById(R.id.tableroPendientes)
        listaEnProgreso = findViewById(R.id.tableroEnCurso)
        listaTerminadas = findViewById(R.id.tableroTerminadas)

        val pendientes = obtenerNoIniciadas()
        val resultado_1 = pendientes.mapIndexed { index, elemento ->
            "• $elemento"
        }.joinToString("\n")
        listaPendientes.text = resultado_1

        val enCurso = obtenerEnProgreso()
        val resultado_2 = enCurso.mapIndexed { index, elemento ->
            "• $elemento"
        }.joinToString("\n")
        listaEnProgreso.text = resultado_2

        val terminadas = obtenerTerminadas()
        val resultado_3 = terminadas.mapIndexed { index, elemento ->
            "• $elemento"
        }.joinToString("\n")
        listaTerminadas.text = resultado_3

    }

    fun backMain(){
        btnBack.setOnClickListener(){
            finish()
        }
    }

    fun obtenerNoIniciadas(): MutableList<String>{

        return modelo.obtenerTitulosPorEstado(0)

    }

    fun obtenerEnProgreso(): MutableList<String>{

        return modelo.obtenerTitulosPorEstado(1)

    }

    fun obtenerTerminadas(): MutableList<String>{

        return modelo.obtenerTitulosPorEstado(2)

    }
}