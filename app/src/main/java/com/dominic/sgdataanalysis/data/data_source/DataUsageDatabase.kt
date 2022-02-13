package com.dominic.sgdataanalysis.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption

@Database(
    entities = [QuarterConsumption::class],
    version = 1
)
abstract class DataUsageDatabase : RoomDatabase() {

    abstract val dataUsageRecordDao: DataUsageRecordDao

    companion object{
        const val DATABASE_NAME = "data_usage_db"
    }

}