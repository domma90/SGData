package com.dominic.sgdataanalysis.viewModel.quarterConsumption

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.sgdataanalysis.domain.use_case.QuarterConsumptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuarterConsumptionViewModel @Inject constructor(private val dispatcher:CoroutineDispatcher,
                                                      private val quarterConsumptionUseCase: QuarterConsumptionUseCase
) :ViewModel() {

    var initialYear:Int = -1

    var uiState by mutableStateOf<QCUIState>(
        QCUIState.EmptyState)
        private set

    fun getUsage(year:Int) = viewModelScope.launch(dispatcher){

        if(initialYear==-1){
            initialYear = quarterConsumptionUseCase.getInitialYear()
        }


        val entries = quarterConsumptionUseCase.getGroupedQuarterUsage()

        uiState = if(entries.isNotEmpty()){
            QCUIState.OnQuarterUsageAvailable(entries,year-initialYear)
        }else{
            QCUIState.Error(message = "Entries not populated")
        }

    }

}
