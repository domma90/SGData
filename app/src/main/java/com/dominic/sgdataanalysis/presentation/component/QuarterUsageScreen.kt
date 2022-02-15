package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment

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

    when(val uiState = viewModel.uiState){
        QCUIState.EmptyState -> viewModel.getUsage(year = year)
        is QCUIState.Error -> {
            Text(text = uiState.message)
        }
        QCUIState.LoadingEntries -> {
            Text(text = "loading")
        }
        is QCUIState.OnQuarterUsageAvailable -> {
            QuarterUsagePage(uiState.quarterUsages,uiState.pageNumber)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun QuarterUsagePage(quarterUsages: List<GroupedQuarterUsage>, pageNumber: Int) {
    val pagerState = rememberPagerState()

    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(count = quarterUsages.size,state = pagerState) { page ->
            Card(elevation = 4.dp, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp,vertical = 50.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = "Year ${quarterUsages[page].year}")


                    Spacer(modifier = Modifier.height(5.dp))

                    val quarterUsagesMap = quarterUsages[page].getMappedQuarterUsage()

                    if(quarterUsagesMap!=null){

                        if(quarterUsagesMap.isNotEmpty()){
                            for(key in quarterUsagesMap.keys){
                                Row() {
                                    Text(key)
                                    Spacer(Modifier.width(10.dp))
                                    Text(quarterUsagesMap[key]?:"")
                                }
                            }
                        }
                    }

                }
            }
        }

    }


    LaunchedEffect(pagerState){
        pagerState.animateScrollToPage(pageNumber)
    }
}