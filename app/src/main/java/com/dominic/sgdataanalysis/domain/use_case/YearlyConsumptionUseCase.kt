package com.dominic.sgdataanalysis.domain.use_case

import android.util.Log
import com.dominic.sgdataanalysis.BuildConfig
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.network.adapter.DataStoreSearchServiceAdapter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YearlyConsumptionUseCase @Inject constructor(
    private val repository: DataUsageRepository,
    private val service: DataStoreSearchServiceAdapter
) {


    fun getYearlyConsumption():Flow<List<YearlyConsumption>>{
        return repository.getYearlyUsage()
    }

    suspend fun retrieveLatestData() {

        try {
            val data = service.getMobileDataUsage(BuildConfig.RESOURCE_ID)
            if (data.isCallSuccess) {

                val dataUsageRecords = data.usageRecords?.map { it ->
                    QuarterConsumption(
                        id = it.id,
                        year = it.getYear(),
                        quarter = it.getQuarterValue(),
                        it.volumeOfMobileData.toFloat()
                    )
                }

                if(dataUsageRecords!=null){
                    repository.insertAllDataUsageRecords(dataUsageRecords)
                }

            }else{
                Log.e("", "retrieveLatestData: "+"no data" )

            }
        }catch (e:Exception){
            Log.e("", "retrieveLatestData: "+e.message )
        }


    }

}