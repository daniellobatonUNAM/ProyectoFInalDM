package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: ImageButton
    private lateinit var btnBoard: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdaptadorTarea
    private lateinit var listaBD: MutableList<Tarea>

    private var conexion = BDSQLite(this)
    private var modelo = ModeloTarea(conexion)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //Se establece la funcionalidadd de los botones para ingresar a otro Activity
        btnAdd = findViewById(R.id.btnAdd)
        btnBoard = findViewById(R.id.btnBoard)
        pasarAddTarea()
        pasarTablero()

        //Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerTareasMain)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Conexión
        modelo = ModeloTarea(conexion)
        //Inserción
        listaBD = modelo.obtenerTodas()

        //Se establece el adaptador
        establecerAdaptador()

    }

    override fun onResume() {

        super.onResume()

        listaBD.clear()

        listaBD = modelo.obtenerTodas()

        establecerAdaptador()
    }

    fun establecerAdaptador(){

        adapter = AdaptadorTarea(listaBD)
        //Listener para cada item
        adapter.setItemClickListener(object : AdaptadorTarea.ItemClickListener {
            override fun onItemClick(tarea: Tarea) {
                Toast.makeText(this@MainActivity, "Tap en " + tarea.titulo, Toast.LENGTH_SHORT).show()
            }
        })
        
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