package com.example.fendipetroleo.data.agente

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgenteDao {
    @Insert
    suspend fun addAgent(agente: Agente)

    @Query("SELECT * FROM minoristascsv")
     fun getAllAgents(): Flow<List<Agente>>

    @Query("SELECT * FROM minoristascsv WHERE codigoSicom = :id")
    suspend fun getUserById(id: Int): Agente?
}