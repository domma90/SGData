package com.dominic.sgdataanalysis.domain.use_case

import android.util.Log
import com.dominic.sgdataanalysis.BuildConfig
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.network.DataStoreSearchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.yield
import javax.inject.Inject

class YearlyConsumptionUseCase @Inject constructor(
    private val repository: DataUsageRepository,
    private val service: DataStoreSearchService
) {


    fun getYearlyConsumption():Flow<List<YearlyConsumption>>{
        return repository.getYearlyUsage()
    }

    suspend fun retrieveLatestData() {

        try {
            val data = service.getMobileDataUsage(BuildConfig.RESOURCE_ID)
            if (data.isSuccessful) {

                val dataUsageRecords = data.body()?.result?.records?.map { it ->
                    QuarterConsumption(
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
        }catch (e:Exception){
            Log.e("", "retrieveLatestData: "+e.message )
        }


    }

}