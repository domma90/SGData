package com.dominic.sgdataanalysis.data.repository

import com.dominic.sgdataanalysis.data.data_source.DataUsageDatabase
import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.domain.model.DataUsageRecord
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.network.DataStoreSearchService
import com.dominic.sgdataanalysis.repository.DataSource
import com.dominic.sgdataanalysis.repository.DataUsage
import com.dominic.sgdataanalysis.repository.DataUsageModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataUsageRepositoryImpl @Inject constructor(
    private val dao: DataUsageRecordDao
) :
    DataUsageRepository {


    override fun getDataUsages(): Flow<List<DataUsageRecord>> {
        return dao.getDataUsages()
    }

    override suspend fun getDataUsageRecordById(id: Int): DataUsageRecord? {
        return dao.getDataUsageRecordById(id)
    }

    override fun getDataUsageRecordsByYear(year: Int): Flow<List<DataUsageRecord>> {
        return dao.getDataUsageRecordsByYear(year)
    }

    override suspend fun insertDataUsageRecord(dataUsageRecord: DataUsageRecord) {
        return dao.insertDataUsageRecord(dataUsageRecord = dataUsageRecord)
    }
}