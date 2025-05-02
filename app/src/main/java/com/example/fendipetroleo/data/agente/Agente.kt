package com.example.fendipetroleo.data.agente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agentes")
data class Agente(
    @PrimaryKey(autoGenerate = false)
    val codigoSicom: Int,
    @ColumnInfo(name = "departamento")
    val departamento: String,
    @ColumnInfo(name = "municipio")
    val municipio: String,
    @ColumnInfo(name = "razonSocial")
    val razonSocial: String,
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "codigoMun")
    val codigoMun: Int
)