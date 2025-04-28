package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fendipetroleo.data.agente.AgenteRepository
import com.example.fendipetroleo.data.volumen.SaleRepository

class AgenteViewModelFactory(private val repository1: AgenteRepository, private val repository2: SaleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgenteViewModel::class.java)) {
            return AgenteViewModel(repository1, repository2) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}