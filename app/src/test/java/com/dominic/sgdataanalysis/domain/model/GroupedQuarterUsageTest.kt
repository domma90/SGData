package com.dominic.sgdataanalysis.domain.model

import org.junit.Assert
import org.junit.Assert.*

import org.junit.Test

class GroupedQuarterUsageTest {

    @Test
    fun `test for map creation from quarters and volumes`() {

        val model = GroupedQuarterUsage(2012,"12.9,23.5,23.18",
        quarters = "Q1,Q2,Q3")

        var quarterUsageMap = model.getMappedQuarterUsage()

        assertEquals(mapOf("Q1" to "12.9" , "Q2" to "23.5", "Q3" to "23.18",),quarterUsageMap)
    }
}