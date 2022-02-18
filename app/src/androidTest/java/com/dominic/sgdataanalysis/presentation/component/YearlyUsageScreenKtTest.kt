package com.dominic.sgdataanalysis.presentation.component

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dominic.sgdataanalysis.presentation.MainActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@ExperimentalPagerApi
@RunWith(AndroidJUnit4::class)
class YearlyUsageScreenKtTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_top_bar(){
        composeTestRule.onNodeWithText("Yearly Usage").assertExists()
    }

    /*NOTE:- Uninstall the SGData app if already installed and disconnect from internet to make this test pass*/
    @Test
    fun test_loading(){
        composeTestRule.onNodeWithText("Loading").assertExists()
    }

    @Test
    fun test_year_exists(){
        composeTestRule.mainClock.autoAdvance = false // default
        composeTestRule.mainClock.advanceTimeBy(10000)
//
//        composeTestRule.waitUntil()

        composeTestRule.onNodeWithText("Year 2006").assertExists()
     }
}