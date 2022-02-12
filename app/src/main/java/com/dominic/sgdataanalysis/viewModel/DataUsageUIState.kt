package com.dominic.sgdataanalysis.viewModel
import com.dominic.sgdataanalysis.dataUsageRepo.DataSource
import com.dominic.sgdataanalysis.dataUsageRepo.DataUsage

sealed class DataUsageUIState{
    object Loading : DataUsageUIState()
    data class OnDataAvailable(val usageData:List<DataUsage>,
                               val dataSource: DataSource
    )
        : DataUsageUIState()

    data class DataError(val message:String): DataUsageUIState()
}