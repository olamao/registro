package com.example.fendipetroleo.data

import androidx.room.Database

import androidx.room.RoomDatabase
import com.example.fendipetroleo.data.agente.Agente
import com.example.fendipetroleo.data.agente.AgenteDao
import com.example.fendipetroleo.data.despachos.Despacho
import com.example.fendipetroleo.data.despachos.DespachoDao

@Database(entities = [Agente::class, Despacho::class], version = 2)
abstract class DMDatabase : RoomDatabase() {
    abstract fun agenteDao(): AgenteDao
    abstract fun despachoDao(): DespachoDao
}