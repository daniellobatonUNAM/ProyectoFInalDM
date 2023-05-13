package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Tablero : AppCompatActivity() {

    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_tablero)

        btnBack = findViewById(R.id.btnBackBoard)
        backMain()
    }

    fun backMain(){
        btnBack.setOnClickListener(){
            finish()
        }
    }
}