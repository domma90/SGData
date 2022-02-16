package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dominic.sgdataanalysis.presentation.navigation.Screen
import com.dominic.sgdataanalysis.viewModel.yearlyConsumption.DataUsageUIState
import com.dominic.sgdataanalysis.viewModel.yearlyConsumption.YearlyUsageViewModel

@Composable
fun YearlyUsageScreen(
    navController: NavController,
    viewModel: YearlyUsageViewModel = hiltViewModel()
) {

//    val scaffoldState = rememberScaffoldState()

    Scaffold(content = {body(viewModel,navController)},
        topBar = { TopAppBar(title = {Text("Yearly Usage")})  }
    )


}

@Composable
fun body(viewModel: YearlyUsageViewModel, navController: NavController){

    when (val uiState = viewModel.uiState) {

        is DataUsageUIState.Empty -> {
            viewModel.loadYearlyData()
            viewModel.retrieveNewData()
        }

        is DataUsageUIState.Loading -> {
            Text(text = "Loading")
        }

        is DataUsageUIState.DataError -> {
            Text(text =uiState.message)
        }

        is DataUsageUIState.OnYearlyUsageAvailable -> {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.yearlyConsumption) { data ->
                    UsageItem(
                        year = data.year.toString(),
                        usage = data.consumption.toString(),
                        onClick = {
                            navController.navigate(Screen.QuarterUsageScreen.withArgs(data.year.toString()))
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun UsageItem(year: String, usage: String, onClick: () -> Unit) {
    val padding = 8.dp

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(padding)
            .fillMaxWidth()
    ) {
        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Year $year")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Consumption $usage")
            }
        }
    }
}