package com.dominic.sgdataanalysis.network

import com.google.gson.annotations.SerializedName

data class DataUsageResponse(
    val help: String,
    val success: Boolean,
    val result: Result
)

data class Result(
    val resourceID: String,
     val records: List<Record>,
)

data class Record(
    @SerializedName("_id") val id: Int,
    @SerializedName("quarter") val quarter: String,
    @SerializedName("volume_of_mobile_data") val volumeOfMobileData: String
){
    fun getYear(): Int {
        return quarter.split("-")[0].toInt()
    }

    fun getQuarterValue(): String {
        return quarter.split("-")[1]
    }



}
