package com.dominic.sgdataanalysis.repository

data class DataUsageModel(
    val dataUsages: List<DataUsage>?,
    val source: DataSource,
    val hasError: Boolean,
    val message: String? = "",
){
    fun hasData():Boolean{
        return dataUsages!=null && dataUsages.isNotEmpty()
    }
}