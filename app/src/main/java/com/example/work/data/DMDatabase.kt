package com.example.work.data

import androidx.room.Database

import androidx.room.RoomDatabase
import com.example.work.data.agente.Agent
import com.example.work.data.agente.AgenteDao
import com.example.work.data.volumen.Despacho
import com.example.work.data.volumen.DespachoDao

@Database(entities = [Agent::class, Despacho::class], version = 3)
abstract class DMDatabase : RoomDatabase() {
    abstract fun agenteDao(): AgenteDao
    abstract fun saleDao(): DespachoDao
}