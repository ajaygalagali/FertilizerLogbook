package com.astro.fertilizerlogbook.repository

import com.astro.fertilizerlogbook.db.FertilizerDatabase
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.models.HistoryModel

class FertilizerRepository(
    val db : FertilizerDatabase
) {
    // Catalog Operations
    suspend fun upsertCatalog(item : FertilizerModel) = db.getFertilizerDao().upsertCatalog(item)
    suspend fun deleteCatalog(item : FertilizerModel) = db.getFertilizerDao().deleteCatalog(item)
    fun getAllFertilizers() = db.getFertilizerDao().getAllFertilizers()

    // History Operations
    suspend fun upsertHistory(item : HistoryModel) = db.getFertilizerDao().upsertHistory(item)
    suspend fun deleteHistory(item : HistoryModel) = db.getFertilizerDao().deleteHistory(item)
    fun getAllHistoryItems() = db.getFertilizerDao().getAllHistoryItems()

}