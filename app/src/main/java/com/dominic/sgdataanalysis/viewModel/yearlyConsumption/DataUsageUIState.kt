package com.dominic.sgdataanalysis.viewModel.yearlyConsumption

import com.dominic.sgdataanalysis.domain.model.YearlyConsumption

sealed class DataUsageUIState {
    object Empty : DataUsageUIState()
    object Loading : DataUsageUIState()
    data class OnYearlyUsageAvailable(val yearlyConsumption: List<YearlyConsumption>):
        DataUsageUIState()
    data class DataError(val message: String) : DataUsageUIState()
}