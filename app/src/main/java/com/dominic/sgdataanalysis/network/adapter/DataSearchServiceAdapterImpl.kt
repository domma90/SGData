package com.dominic.sgdataanalysis.network.adapter

import com.dominic.sgdataanalysis.network.DataStoreSearchService
import javax.inject.Inject

class DataSearchServiceAdapterImpl @Inject constructor(private val dataStoreSearchService: DataStoreSearchService) : DataStoreSearchServiceAdapter {
    override suspend fun getMobileDataUsage(resourceId: String?): DataSearchResponseModel {
       val res = dataStoreSearchService.getMobileDataUsage(resourceId = resourceId)

        return if(res.isSuccessful){
            DataSearchResponseModel(res.body()?.result?.records,true)
        }else{
            DataSearchResponseModel(null,false,message = res.message())
        }

    }
}