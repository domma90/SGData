package com.dominic.sgdataanalysis.domain.model

import android.os.storage.StorageVolume
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataUsageRecord(
    @PrimaryKey val id: Int,
    val year:Int,
    val quarter:String,
    val volume: Float
)