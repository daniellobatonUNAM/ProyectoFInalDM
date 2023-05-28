package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UpdateTarea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_update_tarea)

        val tarea = intent.getParcelableExtra("extra_update", Tarea::class.java)!!


    }
}