package com.dominic.sgdataanalysis.viewModel.yearlyConsumption

import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before

class YearlyUsageViewModelUnitTest{

    private val repository = mockk<DataUsageRepository>()


    @Before
    fun setUp(){
        coEvery {repository.getInitialYear()} returns 2019
    }

}