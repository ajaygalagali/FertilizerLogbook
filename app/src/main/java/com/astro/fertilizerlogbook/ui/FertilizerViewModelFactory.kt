package com.astro.fertilizerlogbook.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astro.fertilizerlogbook.db.repository.FertilizerRepository

class FertilizerViewModelFactory(
    val repository: FertilizerRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FertilizerViewModel(repository) as T
    }
}