package com.dominic.sgdataanalysis.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.sgdataanalysis.dataUsageRepo.DataUsageRepo
import com.dominic.sgdataanalysis.viewModel.DataUsageUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataUsageViewModel @Inject constructor(
    private val repository: DataUsageRepo,
    private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _uiState = MutableSharedFlow<DataUsageUIState>()
    val uiStateFlow: SharedFlow<DataUsageUIState> = _uiState.asSharedFlow()

    fun onUiReady(resourceId:String) = viewModelScope.launch(dispatcher){
        _uiState.emit(DataUsageUIState.Loading)

        val dataUsageModel = repository.getDataUsage(resourceId)

        if(!dataUsageModel.hasError && dataUsageModel.hasData()){

            _uiState.emit(DataUsageUIState.OnDataAvailable(dataUsageModel.dataUsages!!,dataUsageModel.source))
        }else{
            _uiState.emit(DataUsageUIState.DataError(dataUsageModel.message?: "network error"))
        }


    }

}