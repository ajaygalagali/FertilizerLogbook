package com.astro.fertilizerlogbook.db.repository

import com.astro.fertilizerlogbook.db.FertilizerDatabase
import com.astro.fertilizerlogbook.models.FertilizerModel

class FertilizerRepository(
    val db : FertilizerDatabase
) {
    suspend fun upsertCatalog(item : FertilizerModel) = db.getFertilizerDao().upsertCatalog(item)
    suspend fun deleteCatalog(item : FertilizerModel) = db.getFertilizerDao().deleteCatalog(item)
    fun getAllFertilizers() = db.getFertilizerDao().getAllFertilizers()

}