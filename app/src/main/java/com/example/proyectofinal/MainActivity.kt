package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnAdd: ImageButton
    private lateinit var btnBoard: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnBoard = findViewById(R.id.btnBoard)
        pasarAddTarea()
        pasarTablero()

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