package com.astro.fertilizerlogbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.astro.fertilizerlogbook.models.FertilizerModel


@Database( entities = [FertilizerModel::class], version = 1)
abstract class FertilizerDatabase : RoomDatabase(){

    abstract fun getFertilizerDao() : FertilizerDAO

    companion object{

        private var instance:FertilizerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                FertilizerDatabase::class.java,
                "fertilizer_db").build()
    }

}