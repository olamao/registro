package com.example.work.data

import com.example.work.data.agente.Agent
import com.example.work.data.agente.AgenteDao
import com.example.work.data.volumen.DespachoDao

class DMRepository(private val agenteDao: AgenteDao, private val despachoDao: DespachoDao) {

    fun getAllAgents() = agenteDao.getAllAgents()
    suspend fun addAgent(agent: Agent) = agenteDao.addAgent(agent)
    suspend fun getAgentById(id: Int) = agenteDao.getUserById(id)

    fun getAllDespachos() = despachoDao.getAllDespachos()
    suspend fun getDespachoById(id: Int) = despachoDao.getDespachoById(id)

}