package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
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
    private lateinit var progressBar: ProgressBar
    private lateinit var porcentajeRead: TextView
    private lateinit var estadoRead: TextView
    private lateinit var descripcionRead: TextView
    private lateinit var recordatorio: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_read_tarea)

        val tarea = intent.getParcelableExtra("extra_tarea", Tarea::class.java)!!

        tituloRead = findViewById(R.id.txtTituloRead)
        fechaFinRead = findViewById(R.id.txtParaElRead)
        progressBar = findViewById(R.id.progressBarProgreso)
        porcentajeRead = findViewById(R.id.txtPorcentajeRead)
        estadoRead = findViewById(R.id.estadoRead)
        descripcionRead = findViewById(R.id.txtDescripcionRead)
        recordatorio = findViewById(R.id.recordatorioRead)

        tituloRead.text = tarea.titulo
        fechaFinRead.text = getString(R.string.para) + ": " + tarea.fechaFinalizacion
        progressBar.progress = tarea.porcentaje!!
        porcentajeRead.text = tarea.porcentaje.toString() + "% completado"
        if(tarea.estado == 0){
            estadoRead.text = getString(R.string.estado) + " No iniciado"
        }
        descripcionRead.text = tarea.descripcion
        recordatorio.text = getString(R.string.recordatorio) + " " + tarea.frecuenciaRecordatorio

        btnEdit = findViewById(R.id.editTarea)
        editTarea(tarea)

        btnDelete = findViewById(R.id.deleteTarea)
        deleteTarea(tarea)

    }


    fun editTarea(tarea: Tarea){

        btnEdit.setOnClickListener(){

            val intent = Intent(this, UpdateTarea::class.java)
            val bundle = Bundle()
            bundle.putParcelable("extra_update", tarea)
            intent.putExtras(bundle)

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