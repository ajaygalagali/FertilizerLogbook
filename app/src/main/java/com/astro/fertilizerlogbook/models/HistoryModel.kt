package com.astro.fertilizerlogbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_history")
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    val hId : Int? = null,
    val items : MutableList<String>,
    val timeStamp : String,
    val crop : String
) {
}