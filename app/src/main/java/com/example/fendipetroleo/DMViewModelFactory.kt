package com.example.fendipetroleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fendipetroleo.data.DMRepository

class DMViewModelFactory(private val repository1: DMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DMViewModel::class.java)) {
            return DMViewModel(repository1) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}