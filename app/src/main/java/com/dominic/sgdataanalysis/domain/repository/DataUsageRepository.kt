package com.dominic.sgdataanalysis.domain.repository

import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import kotlinx.coroutines.flow.Flow

interface DataUsageRepository {

    fun getYearlyUsage():Flow<List<YearlyConsumption>>

    suspend fun getInitialYear():Int


    suspend fun getGroupedQuarterUsage():List<GroupedQuarterUsage>


    suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption)

    suspend fun insertAllDataUsageRecords(records: List<QuarterConsumption>)
}