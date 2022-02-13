package com.dominic.sgdataanalysis.data.data_source

import androidx.room.*
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import kotlinx.coroutines.flow.Flow

@Dao
interface DataUsageRecordDao {

    @Query("SELECT * FROM datausagerecord")
    fun getDataUsages(): Flow<List<QuarterConsumption>>

    @Query("SELECT year,SUM(volume) as consumption From datausagerecord GROUP BY year")
    fun getYearlyUsage():Flow<List<YearlyConsumption>>

    @Query("SELECT * FROM datausagerecord where id = :id")
    suspend fun getDataUsageRecordById(id:Int): QuarterConsumption?

    @Query("SELECT * FROM datausagerecord where year = :year")
    fun getDataUsageRecordsByYear(year: Int):Flow<List<QuarterConsumption>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption)

    @Delete
    suspend fun deleteDataUsageRecord(quarterConsumption: QuarterConsumption)


}