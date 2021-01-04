package com.astro.fertilizerlogbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_fertilizers")
data class FertilizerModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name : String?,
//    var isChecked : Boolean = false
)