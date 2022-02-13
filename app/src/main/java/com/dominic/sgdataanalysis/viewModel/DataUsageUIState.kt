package com.dominic.sgdataanalysis.viewModel

import com.dominic.sgdataanalysis.domain.model.DataUsageRecord
import com.dominic.sgdataanalysis.repository.DataSource
import com.dominic.sgdataanalysis.repository.DataUsage

sealed class DataUsageUIState {
    object Empty : DataUsageUIState()
    object Loading : DataUsageUIState()
    data class OnDataAvailable(
        val usageData: List<DataUsageRecord>
    ) : DataUsageUIState()

    data class DataError(val message: String) : DataUsageUIState()
}