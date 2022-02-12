package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dominic.sgdataanalysis.viewModel.DataUsageViewModel

@Composable
fun YearlyUsageScreen(
    navController: NavController,
    viewModel: DataUsageViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        }
    }
}