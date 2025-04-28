package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fendipetroleo.data.agente.Agente
import com.example.fendipetroleo.data.agente.AgenteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class AgenteViewModel(private val repository: AgenteRepository) : ViewModel() {

    val allAgents: Flow<List<Agente>> = repository.getAllAgents()
    val allVolume: Flow<List<Venta>> = repository.getAllVenta()

    fun addAgent(agente: Agente) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addAgent(agente)
            }
        }
    }
    fun getAgentById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAgentById(id)
            }
        }
    }
}