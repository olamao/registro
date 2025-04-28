package com.example.fendipetroleo.data.volumen

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {
    @Insert
    suspend fun addSale(sale: Sale)

    @Query("SELECT * FROM saleTable")
     fun getAllSales(): Flow<List<Sale>>

    @Query("SELECT * FROM saleTable WHERE saleIndex = :id")
    suspend fun getUserById(id: Int): Sale?
}