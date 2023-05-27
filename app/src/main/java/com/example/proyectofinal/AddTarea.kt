package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
    private lateinit var seccionOpcional: LinearLayout
    private lateinit var txtCambiante: TextView


    //Btn Añadir
    private lateinit var btnAdd: Button

    //Conexión con la base de datos
    private lateinit var conexion: BDSQLite

    private var datosCompletos: Boolean = false


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
        txtCambiante = findViewById(R.id.txtCambiante)


        //Sección opcional dependiendo del switch
        seccionOpcional = findViewById(R.id.seccion_opcional)
        switchRecordatorio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seccionOpcional.visibility = View.VISIBLE
                txtCambiante.text = "Sí"
                datosCompletos = true

            } else {
                seccionOpcional.visibility = View.INVISIBLE
                txtCambiante.text = "No"
                datosCompletos = false

            }
        }

        //Se inicializa el botón de Añadir
        btnAdd = findViewById(R.id.btnAddTarea)

        btnAdd.setOnClickListener() {

            agregarTarea(datosCompletos)

        }

    }

    fun backMain(){
        btnBack.setOnClickListener(){
            finish()
        }
    }

    fun agregarTarea(datosCompletos_f: Boolean){

        Log.e("Datos completos: ", "$datosCompletos_f")


        val titulo = tituloTarea.text.toString()
        val descripcion = descripcionTarea.text.toString()
        val fechaTermino = fechaFin.text.toString()
        val fechaIniciacion = fechaInicio.text.toString()

        val recordatorio: Boolean;

        var frecuencia: String

        if(datosCompletos_f){

            val radioGroup: RadioGroup = findViewById(R.id.radioRecordatorio)

            val radioButtonId = radioGroup.checkedRadioButtonId

            val radioButton: RadioButton = findViewById(radioButtonId)

            recordatorio = switchRecordatorio.isChecked
            frecuencia = radioButton.text.toString()

        }else{

            recordatorio = false
            frecuencia = ""

        }

        Log.e("Prefacio", "Justo antes de la inserción")

        Log.e("Datos", "titulo: $titulo, Descripción: $descripcion, Término: $fechaTermino, Inicio: $fechaIniciacion, Recordatorio: $recordatorio, Frecuencia: $frecuencia")

        //Conexión
        conexion = BDSQLite(this)
        val modelo = ModeloTarea(conexion)

        //Inserción de una nueva Tarea
        val idInsertado = modelo.insertarTarea(Tarea(null, titulo,
            descripcion, fechaTermino, fechaIniciacion,
            recordatorio, frecuencia,null))

        if (idInsertado != -1L) {

            Toast.makeText(this, "Se ha registrado una nueva tarea", Toast.LENGTH_LONG).show()

        } else {

            Toast.makeText(this, "Falló el registro", Toast.LENGTH_LONG).show()

        }

        finish()



    }
}