package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fendipetroleo.data.agente.Agente
import com.example.fendipetroleo.data.DMRepository
import com.example.fendipetroleo.data.despachos.Despacho
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

class DMViewModel(private val repository1: DMRepository) : ViewModel() {

    val allAgents: Flow<List<Agente>> = repository1.getAllAgents()
    val allDespachos: Flow<List<Despacho>> = repository1.getAllDespachos()

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

    suspend fun getDespachoByIdSuspend(id: Int): Despacho {
        return withContext(Dispatchers.IO) {
            repository1.getDespachoById(id)
        }
    }

    fun getDespachoById(id: Int) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository1.getDespachoById(id)
            }
        }
    }
}

