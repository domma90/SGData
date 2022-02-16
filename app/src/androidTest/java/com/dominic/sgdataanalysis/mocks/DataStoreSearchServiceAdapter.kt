package com.dominic.sgdataanalysis.mocks

import com.dominic.sgdataanalysis.network.Record
import com.dominic.sgdataanalysis.network.adapter.DataSearchResponseModel
import com.dominic.sgdataanalysis.network.adapter.DataStoreSearchServiceAdapter
import kotlinx.coroutines.delay

class MockDataStoreSearchServiceAdapter(private val mockData:List<Record>):DataStoreSearchServiceAdapter {
    override suspend fun getMobileDataUsage(resourceId: String?): DataSearchResponseModel {
        return DataSearchResponseModel(mockData,isCallSuccess = true,"")
    }
}