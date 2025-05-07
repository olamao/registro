package com.example.fendipetroleo.data

import androidx.room.Database

import androidx.room.RoomDatabase
import com.example.fendipetroleo.data.agente.Agent
import com.example.fendipetroleo.data.agente.AgenteDao
import com.example.fendipetroleo.data.volumen.Despacho
import com.example.fendipetroleo.data.volumen.DespachoDao

@Database(entities = [Agent::class, Despacho::class], version = 3)
abstract class DMDatabase : RoomDatabase() {
    abstract fun agenteDao(): AgenteDao
    abstract fun saleDao(): DespachoDao
}