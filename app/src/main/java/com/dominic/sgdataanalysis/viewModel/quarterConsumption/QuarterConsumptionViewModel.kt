package com.dominic.sgdataanalysis.viewModel.quarterConsumption

import androidx.lifecycle.ViewModel
import com.dominic.sgdataanalysis.domain.use_case.QuarterConsumptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class QuarterConsumptionViewModel @Inject constructor(private val dispatcher:CoroutineDispatcher,
                                                      private val quarterConsumptionUseCase: QuarterConsumptionUseCase
) :ViewModel() {


}