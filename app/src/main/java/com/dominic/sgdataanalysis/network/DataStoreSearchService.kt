package com.dominic.sgdataanalysis.network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataStoreSearchService {

    @GET("action/datastore_search")
    suspend fun getMobileDataUsage(@Query("resource_id") resourceId: String?):Response<DataUsageResponse>


}