package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ReadTarea : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_tarea)

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