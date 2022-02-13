package com.dominic.sgdataanalysis.presentation.navigation

sealed class Screen(val route:String) {
    object YearlyUsageScreen: Screen("data_stats_screen")
    object QuarterUsageScreen: Screen("quarter_data_screen")
}