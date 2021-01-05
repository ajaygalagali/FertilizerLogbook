package com.astro.fertilizerlogbook.db

import androidx.room.TypeConverter

class Tconverters {

    @TypeConverter
    fun fromList(list : MutableList<String>) :String{
        return list.joinToString(separator = ",")

    }

    @TypeConverter
    fun fromString(string : String) : List<String>{
        return string.split(",").map {
            it
        }
    }

}