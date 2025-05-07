package com.example.fendipetroleo.data

import com.example.fendipetroleo.data.agente.Agent
import com.example.fendipetroleo.data.agente.AgenteDao
import com.example.fendipetroleo.data.volumen.DespachoDao

class DMRepository(private val agenteDao: AgenteDao, private val despachoDao: DespachoDao) {

    fun getAllAgents() = agenteDao.getAllAgents()
    suspend fun addAgent(agent: Agent) = agenteDao.addAgent(agent)
    suspend fun getAgentById(id: Int) = agenteDao.getUserById(id)

    fun getAllDespachos() = despachoDao.getAllDespachos()
    suspend fun getDespachoById(id: Int) = despachoDao.getDespachoById(id)

}