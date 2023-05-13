package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

class AddTarea : AppCompatActivity() {

    //Btn Back
    private lateinit var btnBack: ImageButton

    //Campos
    private lateinit var tituloTarea: TextView
    private lateinit var descripcionTarea: TextView
    private lateinit var fechaFin: TextView
    private lateinit var fechaInicio: TextView
    private lateinit var switchRecordatorio: SwitchCompat
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioSi: RadioButton
    private lateinit var radioNo: RadioButton

    //Btn Añadir
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_add_tarea)

        btnBack = findViewById(R.id.btnBackAdd)
        backMain()

        //Se inicializan los campos
        tituloTarea = findViewById(R.id.inputNombreTarea)
        descripcionTarea = findViewById(R.id.inputDescripcion)
        fechaFin = findViewById(R.id.inputFechaFin)
        fechaInicio = findViewById(R.id.inputFechaInicio)
        switchRecordatorio = findViewById(R.id.switchRecordatorio)
        radioGroup = findViewById(R.id.radioRecordatorio)
        radioSi = findViewById(R.id.radioSi)
        radioNo = findViewById(R.id.radioNo)

        //Se inicializa el botón de Añadir
        btnAdd = findViewById(R.id.btnAddTarea)

        agregarTarea()

    }

    fun backMain(){
        btnBack.setOnClickListener(){
            finish()
        }
    }

    fun agregarTarea(){

        btnAdd.setOnClickListener(){

            Log.e("Nombre de la tarea: ", tituloTarea.text.toString())
            Log.e("Descripcion de la tarea: ", descripcionTarea.text.toString())
            Log.e("Fecha fin de la tarea: ", fechaFin.text.toString())
            Log.e("Fecha inicio de la tarea: ", fechaInicio.text.toString())
            if(switchRecordatorio.isChecked){

                Log.e("¿Recordatorio de la tarea? ", "Sí")

            }else{

                Log.e("¿Recordatorio de la tarea? ", "No")

            }

            if(radioGroup.checkedRadioButtonId == R.id.radioSi){

                Log.e("Radio de la tarea: ", "Sí")

            }else{

                Log.e("Radio de la tarea: ", "No")

            }
        }

    }
}