package com.dominic.sgdataanalysis.domain.model

import androidx.room.Ignore
import androidx.room.util.StringUtil
import java.lang.Exception

data class GroupedQuarterUsage(val year:Int,  val volumes:String,  val quarters:String){

    fun getMappedQuarterUsage(): Map<String, String>? {
        return try {
            val quartersList:List<String> = quarters.split(",")
            val volumesList:List<String> = volumes.split(",")
            quartersList.zip(volumesList).toMap()

        }catch (e:Exception){
            null
        }
    }
}