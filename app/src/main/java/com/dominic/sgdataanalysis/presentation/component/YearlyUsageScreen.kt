package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dominic.sgdataanalysis.BuildConfig
import com.dominic.sgdataanalysis.presentation.navigation.Screen
import com.dominic.sgdataanalysis.viewModel.DataUsageUIState
import com.dominic.sgdataanalysis.viewModel.DataUsageViewModel

@Composable
fun YearlyUsageScreen(
    navController: NavController,
    viewModel: DataUsageViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val screenState by viewModel.uiStateFlow.collectAsState(initial = DataUsageUIState.Empty)

    viewModel.retrieveNewData()
    viewModel.onUiReady()

    Scaffold(scaffoldState = scaffoldState) {

        when (screenState) {
            is DataUsageUIState.Loading -> {
                Text(text = "Loading")
            }

            is DataUsageUIState.DataError -> {
                Text(text = "On Error")
            }

            is DataUsageUIState.OnDataAvailable -> {

                val data = screenState as DataUsageUIState.OnDataAvailable

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data.usageData) { data ->
//                        Text(text = data.volume)
                        UsageItem(onClick = {
                            navController.navigate(Screen.QuarterUsageScreen.route)
                        })
                    }
                }
            }
        }


    }
}

@Composable
fun UsageItem(onClick: () -> Unit) {
    val padding = 8  .dp

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(padding)
            .fillMaxWidth()
    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) { /*...*/ }
//        Spacer(Modifier.size(padding))
        Card(elevation = 4.dp) {
            Column() {

                Text(text = "Hi")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Hello")
            }
        }


    }
}