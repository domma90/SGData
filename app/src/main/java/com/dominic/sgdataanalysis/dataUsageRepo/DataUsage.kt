package com.dominic.sgdataanalysis.dataUsageRepo
import android.os.storage.StorageVolume


data class DataUsage(val volume: String, val quarter: String)
/* json response from api, for response

"volume_of_mobile_data": "0.000384",
                "quarter": "2004-Q3",
                "_id": 1*/