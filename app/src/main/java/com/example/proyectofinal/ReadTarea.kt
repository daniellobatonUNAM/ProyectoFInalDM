package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReadTarea : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: ImageButton

    //private lateinit var tarea?: Tarea


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_tarea)

        val tarea = intent.getParcelableExtra("extra_tarea", Tarea::class.java)!!

        //Toast.makeText(this, "" + tarea.titulo, Toast.LENGTH_SHORT).show()

        btnEdit = findViewById(R.id.editTarea)
        editTarea()
    }


    fun editTarea(){
        btnEdit.setOnClickListener(){
            val intent = Intent(this, UpdateTarea::class.java)
            startActivity(intent)
        }
    }

}