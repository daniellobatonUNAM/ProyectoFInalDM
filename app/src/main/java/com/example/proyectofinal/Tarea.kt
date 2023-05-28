package com.example.proyectofinal

import android.os.Parcel
import android.os.Parcelable

data class Tarea (
    val id: Long?,
    val titulo: String,
    val descripcion: String,
    val fechaFinalizacion: String,
    val fechaInicio: String,
    val deseaRecoratorio: Boolean?,
    val estado: Int?,
    val frecuenciaRecordatorio: String?,
    val porcentaje: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(titulo)
        parcel.writeString(descripcion)
        parcel.writeString(fechaFinalizacion)
        parcel.writeString(fechaInicio)
        parcel.writeValue(deseaRecoratorio)
        parcel.writeValue(estado)
        parcel.writeString(frecuenciaRecordatorio)
        parcel.writeValue(porcentaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea {
            return Tarea(parcel)
        }

        override fun newArray(size: Int): Array<Tarea?> {
            return arrayOfNulls(size)
        }
    }
}
