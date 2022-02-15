package com.dominic.sgdataanalysis.domain.use_case

import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuarterConsumptionUseCase @Inject constructor(private val repository: DataUsageRepository) {

    suspend fun getInitialYear(): Int {
        return repository.getInitialYear()
    }


    suspend fun getGroupedQuarterUsage(): List<GroupedQuarterUsage> {
        return repository.getGroupedQuarterUsage()
    }
}