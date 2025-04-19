package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AgenteViewModelFactory(private val repository: AgenteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgenteViewModel::class.java)) {
            return AgenteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}