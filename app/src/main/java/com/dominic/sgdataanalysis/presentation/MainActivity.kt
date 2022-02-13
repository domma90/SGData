package com.dominic.sgdataanalysis.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dominic.sgdataanalysis.presentation.component.QuarterUsageScreen
import com.dominic.sgdataanalysis.presentation.component.YearlyUsageScreen
import com.dominic.sgdataanalysis.presentation.navigation.Screen
import com.dominic.sgdataanalysis.ui.theme.SGDataAnalysisTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGDataAnalysisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.YearlyUsageScreen.route
                    ) {
                        composable(route = Screen.YearlyUsageScreen.route) {
                            YearlyUsageScreen(navController = navController)
                        }

                        composable(
                            route = Screen.QuarterUsageScreen.route + "/{year}",
                            arguments = listOf(
                                navArgument(name = "year") {
                                    type = NavType.StringType
                                    defaultValue = "2020"
                                    nullable = true
                                }
                            )
                        ) { backStackEntry ->
                            QuarterUsageScreen(
                                backStackEntry.arguments?.getString("year"),
                            )
                        }
                    }
                }
            }
        }
    }
}
