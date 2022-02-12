package com.dominic.sgdataanalysis.presentation

sealed class Screen(val route:String) {
    object YearlyUsageScreen: Screen("data_stats_screen")
    object QuarterDataScreen:Screen("quarter_data_screen")
}