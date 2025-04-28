package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fendipetroleo.data.agente.Agente
import com.example.fendipetroleo.data.agente.AgenteRepository
import com.example.fendipetroleo.data.volumen.Sale
import com.example.fendipetroleo.data.volumen.SaleRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class AgenteViewModel(private val repository1: AgenteRepository, private val repository2: SaleRepository) : ViewModel() {

    val allAgents: Flow<List<Agente>> = repository1.getAllAgents()
    val allVolume: Flow<List<Sale>> = repository2.getAllSales()

    fun addAgent(agente: Agente) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository1.addAgent(agente)
            }
        }
    }
    fun getAgentById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository1.getAgentById(id)
            }
        }
    }
}