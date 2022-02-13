package com.dominic.sgdataanalysis.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
//                        startDestination = "screen1"
                    ) {
                        composable(route = Screen.YearlyUsageScreen.route) {
                            YearlyUsageScreen(navController = navController)
                        }

                        composable(
                            route = Screen.QuarterUsageScreen.route + "/{year}",
                            arguments = listOf(
                                navArgument(name = "year") {
                                    type = NavType.IntType
                                    defaultValue = 2019
                                }
                            )
                        ) { backStackEntry ->

                            Log.d("TAG", "onCreate: backStackEntry")

                            QuarterUsageScreen(
                                backStackEntry.arguments?.getInt("year")?:2019,
                            )
                        }

                      /*  composable(route = "screen1"){
                            Log.d("TAG", "onCreate: backStackEntry screen1")

                            screen1(navController = navController)
                        }

                        composable(route = "screen2"){
                            Log.d("TAG", "onCreate: backStackEntry screen2")

                            screen2()
                        }*/
                    }
                }
            }
        }
    }
}

@Composable
fun screen1(navController: NavController){
    Button(onClick = { navController.navigate("screen2") }) {
        Row {
            Text(text = "Ok")
        }
    }
}


@Composable
fun screen2(){
    Row {
        Text(text = "Second Screen")
    }
}