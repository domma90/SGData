package com.dominic.sgdataanalysis.data.data_source

import androidx.room.*
import com.dominic.sgdataanalysis.domain.model.DataUsageRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface DataUsageRecordDao {

    @Query("SELECT * FROM datausagerecord")
    fun getDataUsages(): Flow<List<DataUsageRecord>>

    @Query("SELECT * FROM datausagerecord where id = :id")
    suspend fun getDataUsageRecordById(id:Int): DataUsageRecord?

    @Query("SELECT * FROM datausagerecord where year = :year")
    fun getDataUsageRecordsByYear(year: Int):Flow<List<DataUsageRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUsageRecord(dataUsageRecord: DataUsageRecord)

    @Delete
    suspend fun deleteDataUsageRecord(dataUsageRecord: DataUsageRecord)


}