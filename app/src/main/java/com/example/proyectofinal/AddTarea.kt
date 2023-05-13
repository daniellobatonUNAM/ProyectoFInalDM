package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AddTarea : AppCompatActivity() {

    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_add_tarea)

        btnBack = findViewById(R.id.btnBackAdd)
        backMain()
    }

    fun backMain(){
        btnBack.setOnClickListener(){
            finish()
        }
    }
}