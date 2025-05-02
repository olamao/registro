package com.example.fendipetroleo.data

import com.example.fendipetroleo.data.agente.Agente
import com.example.fendipetroleo.data.agente.AgenteDao
import com.example.fendipetroleo.data.despachos.Despacho
import com.example.fendipetroleo.data.despachos.DespachoDao

class DMRepository(private val agenteDao: AgenteDao, private val despachoDao: DespachoDao) {

    fun getAllAgents() = agenteDao.getAllAgents()
    suspend fun addAgent(agente: Agente) = agenteDao.addAgent(agente)
    suspend fun getAgentById(id: Int) = agenteDao.getUserById(id)

    fun getAllDespachos() = despachoDao.getAllDespachos()
    suspend fun addDespacho(despacho: Despacho) = despachoDao.addDespacho(despacho)
    suspend fun getDespachoById(id: Int) = despachoDao.getDespachoById(id)
}