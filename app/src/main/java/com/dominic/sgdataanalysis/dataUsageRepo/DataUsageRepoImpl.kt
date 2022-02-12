package com.dominic.sgdataanalysis.dataUsageRepo
import com.dominic.sgdataanalysis.network.DataStoreSearchService
import javax.inject.Inject

class DataUsageRepoImpl @Inject constructor(private val dataStoreSearchService: DataStoreSearchService) :
    DataUsageRepo {


    override suspend fun getDataUsage(resourceId: String): DataUsageModel {
        val res = dataStoreSearchService.getMobileDataUsage(resourceId)

        return if (res.isSuccessful) {

            val dataUsages: List<DataUsage>? = res.body()?.result?.records?.map { record ->
                DataUsage(
                    volume = record.volumeOfMobileData,
                    quarter = record.quarter
                )
            }

            DataUsageModel(dataUsages, DataSource.Api,false)

        } else {
            DataUsageModel(null, DataSource.Api,true,res.message())
        }

    }
}