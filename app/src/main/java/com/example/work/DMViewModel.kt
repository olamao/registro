package com.example.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.example.work.data.DMRepository
import com.example.work.data.agente.Agent
import com.example.work.data.volumen.Despacho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DMViewModel(private val repository1: DMRepository) : ViewModel() {

    val allAgents: Flow<List<Agent>> = repository1.getAllAgents()
    val allVolume: Flow<List<Despacho>> = repository1.getAllDespachos()

    var municipio: Int = 44430
    var producto: Int = 1
    var despacho: Int = municipio*1000 + producto
    var viewModelPoints = mutableListOf(Point(x=0f, y= 0f))

    fun addAgent(agent: Agent) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository1.addAgent(agent)
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

    fun getDespachoById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository1.getDespachoById(id)
            }
        }
    }
}