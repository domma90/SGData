package com.dominic.sgdataanalysis.network.adapter

import com.dominic.sgdataanalysis.network.DataUsageResponse
import retrofit2.Response
import retrofit2.http.Query

interface DataStoreSearchServiceAdapter {

    suspend fun getMobileDataUsage(@Query("resource_id") resourceId: String?): DataSearchResponseModel

}