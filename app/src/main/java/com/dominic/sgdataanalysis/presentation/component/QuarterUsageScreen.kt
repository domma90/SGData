package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.navigation.NavController
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@Composable
fun QuarterUsageScreen (
    year:Int,
){
    val pagerState = rememberPagerState()

    HorizontalPager(count = 10, state = pagerState) { page ->
        // Our page content
        Text(
            text = "Page: $page $year",
            modifier = Modifier.fillMaxWidth()
        )
    }

    LaunchedEffect(pagerState){
        pagerState.animateScrollToPage(5)
    }
}