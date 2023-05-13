package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: ImageButton
    private lateinit var btnBoard: ImageButton
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //Se establece la funcionalidadd e los botones de regresar
        btnAdd = findViewById(R.id.btnAdd)
        btnBoard = findViewById(R.id.btnBoard)
        pasarAddTarea()
        pasarTablero()

        //Llenado del RecyclerView
        recyclerView = findViewById(R.id.recyclerTareasMain)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Lista que llenará el RecyclerView
        val dataList = listOf(

            Tarea(null, "Tarea 1","19-12-1998", 85),
            Tarea(null, "Tarea 2", "19-12-1997", 84),
            Tarea(null, "Tarea 3", "19-12-1996", 83),

        )
        //Se establece el adaptador
        val adapter = AdaptadorTarea(dataList)
        recyclerView.adapter = adapter

    }

    private fun pasarAddTarea(){
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddTarea::class.java)
            startActivity(intent)
        }
    }

    private fun pasarTablero(){
        btnBoard.setOnClickListener {
            val intent = Intent(this, Tablero::class.java)
            startActivity(intent)
        }
    }

}