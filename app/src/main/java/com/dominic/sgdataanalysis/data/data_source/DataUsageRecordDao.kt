package com.dominic.sgdataanalysis.data.data_source

import androidx.room.*
import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import kotlinx.coroutines.flow.Flow

@Dao
interface DataUsageRecordDao {


    @Query("SELECT year,SUM(volume) as consumption From quarterconsumption GROUP BY year")
    fun getYearlyUsage():Flow<List<YearlyConsumption>>

    @Query("SELECT year, group_concat(volume) as volumes , group_concat(quarter) as quarters FROM quarterconsumption GROUP BY year")
    suspend fun getGroupedQuarterUsage():List<GroupedQuarterUsage>

    @Query("SELECT year from quarterconsumption ORDER BY year ASC LIMIT 1")
    suspend fun getInitialYear():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDataUsageRecords(consumptions: List<QuarterConsumption>)

}

