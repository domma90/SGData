package com.dominic.sgdataanalysis.data.repository

import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataUsageRepositoryImpl @Inject constructor(
    private val dao: DataUsageRecordDao
) :
    DataUsageRepository {


    override fun getDataUsages(): Flow<List<QuarterConsumption>> {
        return dao.getDataUsages()
    }

    override fun getYearlyUsage(): Flow<List<YearlyConsumption>> {
        return dao.getYearlyUsage()
    }

    override suspend fun getDataUsageRecordById(id: Int): QuarterConsumption? {
        return dao.getDataUsageRecordById(id)
    }

    override fun getDataUsageRecordsByYear(year: Int): Flow<List<QuarterConsumption>> {
        return dao.getDataUsageRecordsByYear(year)
    }

    override suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption) {
        return dao.insertDataUsageRecord(quarterConsumption = quarterConsumption)
    }
}