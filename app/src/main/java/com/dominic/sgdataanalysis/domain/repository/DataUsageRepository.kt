package com.dominic.sgdataanalysis.domain.repository

import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import kotlinx.coroutines.flow.Flow

interface DataUsageRepository {

    fun getDataUsages(): Flow<List<QuarterConsumption>>

    fun getYearlyUsage():Flow<List<YearlyConsumption>>

    suspend fun getInitialYear():Int

    suspend fun getDataUsageRecordById(id:Int): QuarterConsumption?

    fun getQuarterConsumptionForYear(year: Int):Flow<List<QuarterConsumption>>

    suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption)

    suspend fun insertAllDataUsageRecords(records: List<QuarterConsumption>)
}