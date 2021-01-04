package com.astro.fertilizerlogbook.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.fertilizerlogbook.repository.FertilizerRepository
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.models.HistoryModel
import kotlinx.coroutines.launch

class FertilizerViewModel(
    private val repository: FertilizerRepository
) : ViewModel(){


    // Catalog Operations
    fun upsertCatalog(item : FertilizerModel) = viewModelScope.launch {
        repository.upsertCatalog(item)
    }

    fun deleteCatalog(item : FertilizerModel) = viewModelScope.launch {
        repository.deleteCatalog(item)
    }

    fun getAllFertilizers() = repository.getAllFertilizers()


    // History Operations

    fun upsertHistory(item : HistoryModel) = viewModelScope.launch {
        repository.upsertHistory(item)
    }

    fun deleteHistory(item : HistoryModel) = viewModelScope.launch {
        repository.deleteHistory(item)
    }

    fun getAllHistoryItems() = repository.getAllHistoryItems()


}