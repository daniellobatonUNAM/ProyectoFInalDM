package com.example.proyectofinal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import java.text.SimpleDateFormat
import java.util.*

class UpdateTarea : AppCompatActivity() {

    private lateinit var btnBack: ImageButton

    //Campos
    private lateinit var tituloTarea: TextView
    private lateinit var descripcionTarea: TextView
    private lateinit var btnFechaFin: ImageButton
    private lateinit var txtFechaFin: TextView
    private lateinit var btnFechaInicio: ImageButton
    private lateinit var txtFechaInicio: TextView
    private lateinit var txtProgreso: TextView
    private lateinit var sliderProgreso: SeekBar
    private lateinit var switchRecordatorio: SwitchCompat
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioSi: RadioButton
    private lateinit var radioNo: RadioButton
    private lateinit var seccionOpcional: LinearLayout
    private lateinit var txtCambiante: TextView

    private lateinit var fechaFin: String
    private lateinit var fechaInicio: String

    //Btn Añadir
    private lateinit var btnEdit: Button

    //Conexión con la base de datos
    private lateinit var conexion: BDSQLite

    private var datosCompletos: Boolean = false
    private var valorProgress: Int = 0

    private lateinit var tarea: Tarea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_update_tarea)

        tarea = intent.getParcelableExtra("extra_update", Tarea::class.java)!!

        datosCompletos = tarea.deseaRecoratorio!!
        valorProgress = tarea.porcentaje!!

        //Se inicializan los campos
        tituloTarea = findViewById(R.id.inputNombreEdit)
        tituloTarea.text = tarea.titulo //placeholder título
        descripcionTarea = findViewById(R.id.inputDescripcionEdit)
        descripcionTarea.text = tarea.descripcion //placeholder descripción
        btnFechaFin = findViewById(R.id.btnCalendarFinEdit)
        fechaFin = tarea.fechaFinalizacion //placeholder fecha fin
        btnFechaFin.setOnClickListener { mostrarCalendario("Fin") }
        txtFechaFin = findViewById(R.id.textoFechaFin)
        txtFechaFin.text = tarea.fechaFinalizacion
        txtProgreso = findViewById(R.id.txtProgreso)
        txtProgreso.text = tarea.porcentaje.toString()
        sliderProgreso = findViewById(R.id.seekBarProgreso)
        sliderProgreso.progress = tarea.porcentaje!! //placeholder progreso
        btnFechaInicio = findViewById(R.id.btnCalendarInicioEdit)
        fechaInicio = tarea.fechaInicio
        btnFechaInicio.setOnClickListener { mostrarCalendario("Inicio") }
        txtFechaInicio = findViewById(R.id.textoFechaInicio)
        txtFechaInicio.text = tarea.fechaInicio //placeholder fecha inicio
        seccionOpcional = findViewById(R.id.seccion_opcional_edit)
        switchRecordatorio = findViewById(R.id.switchRecordatorioEdit)
        txtCambiante = findViewById(R.id.txtCambiante)
        radioGroup = findViewById(R.id.radioRecordatorioEdit)
        radioSi = findViewById(R.id.radioSiEdit)
        radioNo = findViewById(R.id.radioNoEdit)
        if(tarea.deseaRecoratorio!!){
            switchRecordatorio.isChecked = true
            txtCambiante.text = "Sí"
            seccionOpcional.visibility = View.VISIBLE

            if(tarea.frecuenciaRecordatorio == getString(R.string.cinco_antes)){

                radioSi.isChecked = true

            }else if(tarea.frecuenciaRecordatorio == getString(R.string.uno_antes)){

                radioNo.isChecked = true

            }

        }

        //Sección opcional dependiendo del switch

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

        sliderProgreso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                valorProgress = progress
                txtProgreso.text = progress.toString() + "%"

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}

        })

        btnEdit = findViewById(R.id.btnEditTarea)

        btnEdit.setOnClickListener(){

            actualizarTarea(datosCompletos)

        }

    }

    fun actualizarTarea(datosCompletos_f: Boolean){

        var tareaUpdated = Tarea(null, "",
            "", "", "",
            null, null,
            null, null)

        tareaUpdated.id = tarea.id
        tareaUpdated.titulo = tituloTarea.text.toString().trim()
        tareaUpdated.descripcion = descripcionTarea.text.toString().trim()
        tareaUpdated.fechaFinalizacion = fechaFin
        tareaUpdated.fechaInicio = fechaInicio

        var estado = 0
        if(valorProgress == 0) {
            estado = 0
        }else if(valorProgress > 0 && valorProgress < 100){
            estado = 1
        }else if(valorProgress == 100){
            estado = 2
        }

        var recordatorio: Boolean;
        var frecuencia: String

        if(datosCompletos_f){

            val radioGroup: RadioGroup = findViewById(R.id.radioRecordatorioEdit)

            val radioButtonId = radioGroup.checkedRadioButtonId

            val radioButton: RadioButton = findViewById(radioButtonId)

            recordatorio = switchRecordatorio.isChecked
            frecuencia = radioButton.text.toString().trim()

        }else{

            recordatorio = false
            frecuencia = ""

        }

        tareaUpdated.deseaRecoratorio = recordatorio
        tareaUpdated.estado = estado
        tareaUpdated.frecuenciaRecordatorio = frecuencia
        tareaUpdated.porcentaje = valorProgress


        //Conexión
        conexion = BDSQLite(this)
        val modelo = ModeloTarea(conexion)

        //Inserción de una nueva Tarea
        val idActualizado = modelo.actualizarTarea(tareaUpdated)

        if (idActualizado) {
            Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tarea NO actualizada", Toast.LENGTH_SHORT).show()
        }

        finish()

    }

    private fun mostrarCalendario(tipo: String) {
        val calendar = Calendar.getInstance()
        val anio = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)
        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        val fechaMinima: Calendar = Calendar.getInstance()


        val datePicker = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val fechaSeleccionada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }.time)

                if (tipo == "Fin"){
                    fechaFin = fechaSeleccionada
                    txtFechaFin.text = fechaSeleccionada
                }else{
                    fechaInicio = fechaSeleccionada
                    txtFechaInicio.text = fechaSeleccionada
                }

            }, anio, mes, dia)

        //Fecha mínima (hoy)
        datePicker.datePicker.minDate = fechaMinima.timeInMillis

        // Muestra el diálogo del selector de fecha
        datePicker.show()
    }

}