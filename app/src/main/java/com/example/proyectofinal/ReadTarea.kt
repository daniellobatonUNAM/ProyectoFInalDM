package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ReadTarea : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: ImageButton
    private lateinit var btnDelete: ImageButton

    private lateinit var conexion: BDSQLite


    private lateinit var tituloRead: TextView
    private lateinit var fechaFinRead: TextView
    private lateinit var porcentajeRead: TextView
    private lateinit var descripcionRead: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_read_tarea)

        val tarea = intent.getParcelableExtra("extra_tarea", Tarea::class.java)!!

        tituloRead = findViewById(R.id.txtTituloRead)
        fechaFinRead = findViewById(R.id.txtParaElRead)
        porcentajeRead = findViewById(R.id.txtPorcentajeRead)
        descripcionRead = findViewById(R.id.txtDescripcionRead)

        tituloRead.text = tarea.titulo
        fechaFinRead.text = tarea.fechaFinalizacion
        porcentajeRead.text = tarea.porcentaje.toString()
        descripcionRead.text = tarea.descripcion

        btnEdit = findViewById(R.id.editTarea)
        editTarea()

        btnDelete = findViewById(R.id.deleteTarea)

        Log.e("id Tarea", tarea.id.toString())
        deleteTarea(tarea)
    }


    fun editTarea(){
        btnEdit.setOnClickListener(){
            val intent = Intent(this, UpdateTarea::class.java)
            startActivity(intent)
        }
    }

    fun deleteTarea(tarea: Tarea){

        btnDelete.setOnClickListener(){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmar borrado")
            builder.setMessage("¿Estás seguro de que deseas eliminar esta tarea?")

            builder.setPositiveButton("Confirmar") { dialog, which ->

                conexion = BDSQLite(this)

                val modelo = ModeloTarea(conexion)

                val eliminadaExitosamente = modelo.eliminarTarea(tarea)

                if (eliminadaExitosamente) {

                    Toast.makeText(this, "La tarea se eliminó exitosamente.", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this, "No se pudo eliminar la tarea.", Toast.LENGTH_SHORT).show()

                }

                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->

                dialog.dismiss()

            }

            val dialog = builder.create()
            dialog.show()

        }
    }

}