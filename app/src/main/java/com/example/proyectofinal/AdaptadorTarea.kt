package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AdaptadorTarea (private val dataList: List<Tarea>) : RecyclerView.Adapter<AdaptadorTarea.ViewHolder>() {

    private var itemClickListener: ItemClickListener? = null
    fun setItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewTitulo: TextView = view.findViewById(R.id.tituloItemTarea)
        val textViewFecha: TextView = view.findViewById(R.id.fechaFinalizacion)
        val textViewPorcentaje: TextView = view.findViewById(R.id.porcentajeCumplido)

        fun bind(item: Tarea){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Tarea = dataList[position]
        holder.textViewTitulo.text = item.titulo
        val diasDiferencia = calcularDiferenciaDias(item.fechaFinalizacion, item.fechaInicio)
        if(diasDiferencia == 0){
            holder.textViewFecha.text = "Para hoy"
        }else if(diasDiferencia == 1){
            holder.textViewFecha.text = "Un día restante"
        }else{
            holder.textViewFecha.text = diasDiferencia.toString() + " días restantes"
        }
        holder.textViewPorcentaje.text = item.porcentaje.toString() + "% completada"
        holder.bind(item)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface ItemClickListener {
        fun onItemClick(tarea: Tarea)
    }

    fun calcularDiferenciaDias(fechaInicio: String, fechaFin: String): Int{

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaInicio: Date = dateFormat.parse(fechaInicio)
        val fechaFinalizacion: Date = dateFormat.parse(fechaFin)

        val diferenciaMilisegundos = fechaFinalizacion.time - fechaInicio.time
        val diferenciaDias = TimeUnit.MILLISECONDS.toDays(diferenciaMilisegundos)

        return diferenciaDias.toInt() * -1
    }

}