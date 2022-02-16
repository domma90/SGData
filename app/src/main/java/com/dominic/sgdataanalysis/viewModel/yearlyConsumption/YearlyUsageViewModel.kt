package com.dominic.sgdataanalysis.viewModel.yearlyConsumption

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.sgdataanalysis.domain.use_case.YearlyConsumptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyUsageViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val yearlyConsumptionUseCase: YearlyConsumptionUseCase
) : ViewModel() {

    var uiState by mutableStateOf<DataUsageUIState>(DataUsageUIState.Empty)
        private set

    fun loadYearlyData() = viewModelScope.launch(dispatcher) {
        uiState = DataUsageUIState.Loading

        yearlyConsumptionUseCase.getYearlyConsumption()
            .catch { e -> DataUsageUIState.DataError(e.toString()) }
            .collect {
                uiState = DataUsageUIState.OnYearlyUsageAvailable(it)

            }
    }

    fun retrieveNewData() = viewModelScope.launch(dispatcher) {
        yearlyConsumptionUseCase.retrieveLatestData()
    }
}