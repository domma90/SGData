package com.dominic.sgdataanalysis.dataUsageRepo

import com.dominic.sgdataanalysis.dataUsageRepo.DataUsageModel

interface DataUsageRepo {
    suspend fun getDataUsage(resourceId: String): DataUsageModel
}