package com.example.fendipetroleo.data.volumen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saleTable")
data class Sale(
    @PrimaryKey(autoGenerate = false)
    val saleIndex: Int?,

    @ColumnInfo(name = "anno")
    val departamento: Int?,

    @ColumnInfo(name = "mes")
    val mes: Int?,

    @ColumnInfo(name = "municipio")
    val municipio: String?,

    @ColumnInfo(name = "tipo_comprador")
    val tipo_comprador: String?,

    @ColumnInfo(name = "producto")
    val producto: String?,

    @ColumnInfo(name = "volume")
    val volume: Int?,

)