package com.astro.fertilizerlogbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_crop")
data class CropModel (
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val cropName : String?
)