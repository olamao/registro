package com.example.fendipetroleo

class AgenteRepository(private val agenteDao: AgenteDao) {

    fun getAllAgents() = agenteDao.getAllAgents()
    suspend fun addAgent(agente: Agente) = agenteDao.addAgent(agente)
    suspend fun getAgentById(id: Int) = agenteDao.getUserById(id)
}