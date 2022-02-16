package com.dominic.sgdataanalysis.network.adapter

import com.dominic.sgdataanalysis.network.DataUsageResponse
import com.dominic.sgdataanalysis.network.Record

data class DataSearchResponseModel(
    val usageRecords:List<Record>?,
    val isCallSuccess:Boolean,
    val message:String = ""
){

}