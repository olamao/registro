package com.example.work.data.despachos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DespachoDao {
    @Insert
    suspend fun addDespacho(despacho: Despacho)

    @Query("SELECT * FROM despachos")
     fun getAllDespachos(): Flow<List<Despacho>>

    @Query("SELECT * FROM despachos WHERE indexMunPro = :id")
    suspend fun getDespachoById(id: Int): Despacho
}