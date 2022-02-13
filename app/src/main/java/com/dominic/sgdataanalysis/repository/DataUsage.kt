package com.dominic.sgdataanalysis.repository


data class DataUsage(val volume: String, val quarter: String){
    fun getYear():String {
        return quarter.split("-")[0]
    }
}
