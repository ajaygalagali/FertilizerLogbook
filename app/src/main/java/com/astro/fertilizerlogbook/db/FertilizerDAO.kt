package com.astro.fertilizerlogbook.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.astro.fertilizerlogbook.models.FertilizerModel

@Dao
interface FertilizerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCatalog(item : FertilizerModel)

    @Delete
    suspend fun deleteCatalog(item : FertilizerModel)

    @Query("SELECT * FROM tbl_fertilizers")
    fun getAllFertilizers() : LiveData<List<FertilizerModel>>

}