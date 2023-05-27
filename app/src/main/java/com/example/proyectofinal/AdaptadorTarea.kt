package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorTarea (private val dataList: List<Tarea>) : RecyclerView.Adapter<AdaptadorTarea.ViewHolder>() {

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
        holder.textViewFecha.text = item.fechaFinalizacion
        holder.textViewPorcentaje.text = item.porcentaje.toString() + "% completada"
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}