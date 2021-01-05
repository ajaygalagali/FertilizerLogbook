package com.astro.fertilizerlogbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.astro.fertilizerlogbook.models.CropModel
import com.astro.fertilizerlogbook.models.FertilizerModel
import com.astro.fertilizerlogbook.models.HistoryModel


@Database( entities = [FertilizerModel::class,HistoryModel::class,CropModel::class], version = 2)
@TypeConverters(Tconverters::class)
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