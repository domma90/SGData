package com.dominic.sgdataanalysis.viewModel.yearlyConsumption

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dominic.sgdataanalysis.data.data_source.DataUsageDatabase
import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.data.repository.DataUsageRepositoryImpl
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.domain.use_case.YearlyConsumptionUseCase
import com.dominic.sgdataanalysis.mocks.MockDataStoreSearchServiceAdapter
import com.dominic.sgdataanalysis.network.Record
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class YearlyUsageViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DataUsageDatabase
    private lateinit var dao: DataUsageRecordDao
    private lateinit var repository: DataUsageRepository
    private lateinit var yearlyConsumptionUseCase: YearlyConsumptionUseCase
    private lateinit var mockService:MockDataStoreSearchServiceAdapter
    private lateinit var viewModel:YearlyUsageViewModel

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DataUsageDatabase::class.java).allowMainThreadQueries().build()
        dao = database.dataUsageRecordDao

        val record1 = Record(1,"2000-Q1","1.5")
        val record2 = Record(2,"2000-Q2","1.5")
        val record3 = Record(3,"2000-Q3","2.5")
        val record4 = Record(4,"2000-Q4","3.5")
        val mockData = listOf(record1,record2,record3,record4)

        mockService = MockDataStoreSearchServiceAdapter(mockData)
        repository = DataUsageRepositoryImpl(dao)
        yearlyConsumptionUseCase = YearlyConsumptionUseCase(repository,mockService)
        viewModel = YearlyUsageViewModel(testDispatcher,yearlyConsumptionUseCase)
    }

    @Test
    fun emptyUIStateTest(){
        assertEquals(DataUsageUIState.Empty,viewModel.uiState)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun yearlyConsumptionTest() = runBlockingTest{
        viewModel.retrieveNewData()
        viewModel.loadYearlyData()

        val expected = DataUsageUIState.OnYearlyUsageAvailable(listOf(
            YearlyConsumption(2000,9.0f)
        ))

        assertEquals(expected,viewModel.uiState)
    }

    @After
    fun tearDown() {
        database.close()

    }
}