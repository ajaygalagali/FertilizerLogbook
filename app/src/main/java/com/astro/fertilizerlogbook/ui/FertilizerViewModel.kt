package com.astro.fertilizerlogbook.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.fertilizerlogbook.db.repository.FertilizerRepository
import com.astro.fertilizerlogbook.models.FertilizerModel
import kotlinx.coroutines.launch

class FertilizerViewModel(
    private val repository: FertilizerRepository
) : ViewModel(){

    fun upsertCatalog(item : FertilizerModel) = viewModelScope.launch {
        repository.upsertCatalog(item)
    }

    fun deleteCatalog(item : FertilizerModel) = viewModelScope.launch {
        repository.deleteCatalog(item)
    }

    fun getAllFertilizers() = repository.getAllFertilizers()


}