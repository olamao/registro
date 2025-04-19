package com.example.fendipetroleo

import androidx.room.Database

import androidx.room.RoomDatabase

@Database(entities = [Agente::class], version = 2)
abstract class AgenteDatabase : RoomDatabase() {
    abstract fun agenteDao(): AgenteDao
}