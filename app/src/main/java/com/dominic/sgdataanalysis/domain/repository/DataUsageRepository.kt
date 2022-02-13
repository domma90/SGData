package com.dominic.sgdataanalysis.domain.repository

import com.dominic.sgdataanalysis.domain.model.DataUsageRecord
import kotlinx.coroutines.flow.Flow

interface DataUsageRepository {

    fun getDataUsages(): Flow<List<DataUsageRecord>>

    suspend fun getDataUsageRecordById(id:Int): DataUsageRecord?

    fun getDataUsageRecordsByYear(year: Int):Flow<List<DataUsageRecord>>

    suspend fun insertDataUsageRecord(dataUsageRecord: DataUsageRecord)

}