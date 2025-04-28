package com.example.fendipetroleo.data.volumen

import androidx.room.Database

import androidx.room.RoomDatabase

@Database(entities = [Sale::class], version = 1)
abstract class SaleDatabase : RoomDatabase() {
    abstract fun saleDao(): SaleDao
}