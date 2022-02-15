package com.dominic.sgdataanalysis.viewModel.quarterConsumption

import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage

sealed class QCUIState {
    object EmptyState:QCUIState()
    object LoadingEntries:QCUIState()
    data class OnQuarterUsageAvailable(val quarterUsages:List<GroupedQuarterUsage>,val pageNumber:Int):QCUIState()
    data class Error(val message:String):QCUIState()


}