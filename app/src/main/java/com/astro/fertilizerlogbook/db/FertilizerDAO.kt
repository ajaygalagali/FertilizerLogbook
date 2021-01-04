package com.astro.fertilizerlogbook.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.models.HistoryModel

@Dao
interface FertilizerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCatalog(item : FertilizerModel)

    @Delete
    suspend fun deleteCatalog(item : FertilizerModel)

    @Query("SELECT * FROM tbl_fertilizers")
    fun getAllFertilizers() : LiveData<List<FertilizerModel>>



    // History operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertHistory(item : HistoryModel)

    @Delete
    suspend fun deleteHistory(item : HistoryModel)

    @Query("SELECT * FROm tbl_history")
    fun getAllHistoryItems()  :LiveData<List<HistoryModel>>


}