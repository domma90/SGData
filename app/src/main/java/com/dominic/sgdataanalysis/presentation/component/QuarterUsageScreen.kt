package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable

import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.viewModel.quarterConsumption.QCUIState
import com.dominic.sgdataanalysis.viewModel.quarterConsumption.QuarterConsumptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun QuarterUsageScreen (
    year:Int,
    viewModel: QuarterConsumptionViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState

    when(uiState){
        QCUIState.EmptyState -> viewModel.retrieveQuarterUsage()
        is QCUIState.Error -> {
            Text(text = uiState.message)
        }
        QCUIState.LoadingEntries -> {
            Text(text = "loading")
        }
        is QCUIState.OnQuarterUsageAvailable -> {
            QuarterUsagePage(uiState.quarterUsages)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun QuarterUsagePage(quarterUsages: List<GroupedQuarterUsage>) {
    val pagerState = rememberPagerState()

    HorizontalPager(count = quarterUsages.size,state = pagerState) { page ->
        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Year ${quarterUsages[page].year}")
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "volumes: ${quarterUsages[page].volumes}",
                    modifier = Modifier.fillMaxWidth()
                )
//                Text(text = "Consumption $usage")
            }
        }
    }


}