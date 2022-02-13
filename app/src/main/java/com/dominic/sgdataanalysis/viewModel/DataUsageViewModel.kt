package com.dominic.sgdataanalysis.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dominic.sgdataanalysis.repository.DataSource
import com.dominic.sgdataanalysis.repository.DataUsage
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.data.data_source.DataUsageDatabase
import com.dominic.sgdataanalysis.domain.use_case.DataUsageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataUsageViewModel @Inject constructor(
     private val dispatcher: CoroutineDispatcher,
     private val dataUsageUseCase: DataUsageUseCase
 ) : ViewModel() {

    private val _uiState = MutableSharedFlow<DataUsageUIState>()
    val uiStateFlow: SharedFlow<DataUsageUIState> = _uiState.asSharedFlow()
    var job: Job? = null



//    init {
//
//        job = viewModelScope.launch() {
//            dataUsageDatabase
//                .dataUsageRecordDao
//                .getNotes()
//                .catch { exception -> _uiState.emit(DataUsageUIState.DataError(exception.message.toString())) }
//                .collect {
//                    val items = it.map { dataUsageRecord ->
//                        DataUsage(
//                            volume = dataUsageRecord.volume.toString(),
//                            quarter = dataUsageRecord.quarter
//                        )
//                    }
//
//                    Log.d("TAG", "items from db count is " + items.size.toString())
//
//                    _uiState.emit(
//                        DataUsageUIState.OnDataAvailable(
//                            usageData = items,
//                            dataSource = DataSource.Database
//                        )
//                    )
//                }
//        }
//    }

/*    fun onUiReady(resourceId: String) = viewModelScope.launch(dispatcher) {


        val dataUsageModel = repository.getDataUsage(resourceId)



        if (!dataUsageModel.hasError && dataUsageModel.hasData()) {

            _uiState.emit(
                DataUsageUIState.OnDataAvailable(
                    dataUsageModel.dataUsages!!,
                    dataUsageModel.source
                )
            )
        } else {
            _uiState.emit(DataUsageUIState.DataError(dataUsageModel.message ?: "network error"))
        }


    }*/

/*
    fun onUiReady(resourceId: String) = viewModelScope.launch(dispatcher) {


        val dataUsageModel = repository.getDataUsage(resourceId)



        if (!dataUsageModel.hasError && dataUsageModel.hasData()) {

            retrieveData()



        } else {
            _uiState.emit(DataUsageUIState.DataError(dataUsageModel.message ?: "network error"))
        }


    }
*/


/*
    private fun retrieveData(){
        if(job?.isActive == true){
            job?.cancel()
        }

        job = viewModelScope.launch(dispatcher) {
            dataUsageDatabase
                .dataUsageRecordDao
                .getDataUsages()
                .catch { exception -> _uiState.emit(DataUsageUIState.DataError(exception.message.toString())) }
                .collect {
                    val items = it.map { dataUsageRecord ->
                        DataUsage(
                            volume = dataUsageRecord.volume.toString(),
                            quarter = dataUsageRecord.quarter
                        )
                    }

                    Log.d("TAG", "items from db count is " + items.size.toString())

                    _uiState.emit(
                        DataUsageUIState.OnDataAvailable(
                            usageData = items,
                            dataSource = DataSource.Database
                        )
                    )
                }
        }
    }
*/

    fun onUiReady() = viewModelScope.launch(dispatcher){
        _uiState.emit(
            DataUsageUIState.Loading
        )

        dataUsageUseCase.getDataUsages()
            .catch { e -> DataUsageUIState.DataError(e.toString()) }
            .collect {
                _uiState.emit(
                    DataUsageUIState.OnDataAvailable(it)
                )
            }
    }

    fun retrieveNewData() =viewModelScope.launch(dispatcher) {
        dataUsageUseCase.retrieveLatestData()
    }
}