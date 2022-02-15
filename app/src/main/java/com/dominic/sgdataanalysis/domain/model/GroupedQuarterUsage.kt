package com.dominic.sgdataanalysis.domain.model

import androidx.room.Ignore
import androidx.room.util.StringUtil

class GroupedQuarterUsage(val year:Int,  val volumes:String,  val quarters:String){

    @Ignore
    private val quartersList:List<String> = quarters.split(",")
    @Ignore
    private val volumesList:List<String> = volumes.split(",")
    @Ignore
    val mapQuarterUsage: Map<String, String> = quartersList.zip(volumesList).toMap()
}