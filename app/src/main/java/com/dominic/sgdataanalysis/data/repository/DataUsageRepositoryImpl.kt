package com.dominic.sgdataanalysis.data.repository

import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataUsageRepositoryImpl @Inject constructor(
    private val dao: DataUsageRecordDao
) :
    DataUsageRepository {


    override fun getYearlyUsage(): Flow<List<YearlyConsumption>> {
        return dao.getYearlyUsage()
    }

    override suspend fun getInitialYear(): Int {
        return dao.getInitialYear()
    }



    override suspend fun getGroupedQuarterUsage(): List<GroupedQuarterUsage> {
        return dao.getGroupedQuarterUsage()
    }


    override suspend fun insertDataUsageRecord(quarterConsumption: QuarterConsumption) {
        return dao.insertDataUsageRecord(quarterConsumption = quarterConsumption)
    }

    override suspend fun insertAllDataUsageRecords(records:List<QuarterConsumption>){
        return dao.insertAllDataUsageRecords(records)
    }
}