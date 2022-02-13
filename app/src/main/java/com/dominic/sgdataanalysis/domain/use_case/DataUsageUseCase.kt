package com.dominic.sgdataanalysis.domain.use_case

import android.util.Log
import com.dominic.sgdataanalysis.BuildConfig
import com.dominic.sgdataanalysis.domain.model.DataUsageRecord
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.network.DataStoreSearchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.yield
import javax.inject.Inject

class DataUsageUseCase @Inject constructor(
    private val repository: DataUsageRepository,
    private val service: DataStoreSearchService
) {


    fun getDataUsages(): Flow<List<DataUsageRecord>> {
        return repository.getDataUsages()
    }

    suspend fun retrieveLatestData() {

        val data = service.getMobileDataUsage(BuildConfig.RESOURCE_ID)

        if (data.isSuccessful) {

            val dataUsageRecords = data.body()?.result?.records?.map { it ->
                DataUsageRecord(
                    id = it.id,
                    year = it.getYear(),
                    quarter = it.getQuarterValue(),
                    it.volumeOfMobileData.toFloat()
                )
            }

            dataUsageRecords?.onEach {
                yield() /*check whether the coroutine job canceled*/
                repository.insertDataUsageRecord(it)
            }

        }
    }

}