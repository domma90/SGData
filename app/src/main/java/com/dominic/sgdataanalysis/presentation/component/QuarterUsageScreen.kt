package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable

import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dominic.sgdataanalysis.viewModel.quarterConsumption.QuarterConsumptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun QuarterUsageScreen (
    year:Int,
    viewModel: QuarterConsumptionViewModel = hiltViewModel()
){
    val pagerState = rememberPagerState()

    HorizontalPager(count = 10, state = pagerState) { page ->
        // Our page content

        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Year $year")
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Page: $page $year",
                    modifier = Modifier.fillMaxWidth()
                )
//                Text(text = "Consumption $usage")
            }
        }
    }

    LaunchedEffect(pagerState){
        pagerState.animateScrollToPage(5)
    }
}