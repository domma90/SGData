package com.dominic.sgdataanalysis.viewModel.yearlyConsumption

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

    private val _uiState = MutableSharedFlow<DataUsageUIState>()
    val uiStateFlow: SharedFlow<DataUsageUIState> = _uiState.asSharedFlow()

    fun onUiReady() = viewModelScope.launch(dispatcher) {
        _uiState.emit(
            DataUsageUIState.Loading
        )
        yearlyConsumptionUseCase.getYearlyConsumption()
            .catch { e -> DataUsageUIState.DataError(e.toString()) }
            .collect {
                _uiState.emit(
                    DataUsageUIState.OnYearlyUsageAvailable(it)
                )
            }
    }

    fun retrieveNewData() = viewModelScope.launch(dispatcher) {
        yearlyConsumptionUseCase.retrieveLatestData()
    }
}