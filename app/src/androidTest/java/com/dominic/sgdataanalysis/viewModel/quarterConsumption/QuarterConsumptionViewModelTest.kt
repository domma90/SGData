package com.dominic.sgdataanalysis.viewModel.quarterConsumption

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dominic.sgdataanalysis.data.data_source.DataUsageDatabase
import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.data.repository.DataUsageRepositoryImpl
import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.domain.use_case.QuarterConsumptionUseCase
import com.dominic.sgdataanalysis.viewModel.yearlyConsumption.YearlyUsageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuarterConsumptionViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DataUsageDatabase
    private lateinit var dao: DataUsageRecordDao
    private lateinit var repository: DataUsageRepository
    private lateinit var quarterConsumptionUseCase: QuarterConsumptionUseCase
    private lateinit var viewModel: QuarterConsumptionViewModel

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DataUsageDatabase::class.java).allowMainThreadQueries().build()
        dao = database.dataUsageRecordDao

        repository = DataUsageRepositoryImpl(dao)
        quarterConsumptionUseCase = QuarterConsumptionUseCase(repository)
        viewModel = QuarterConsumptionViewModel(testDispatcher,quarterConsumptionUseCase)
    }

    @Test
    fun testEmptyState() = runBlockingTest{
        assertEquals(QCUIState.EmptyState,viewModel.uiState)

    }

    @Test
    fun testQuarterUsage() = runBlockingTest{
        val r1 = QuarterConsumption(1,2000,"Q1",1f)
        val r2 = QuarterConsumption(2,2000,"Q2",1.5f)
        val r3 = QuarterConsumption(3,2000,"Q3",2.5f)
        val r4 = QuarterConsumption(4,2000,"Q4",1f)

        val r5 = QuarterConsumption(5,2001,"Q1",2f)
        val r6 = QuarterConsumption(6,2001,"Q2",1.5f)
        val r7 = QuarterConsumption(7,2001,"Q3",2.5f)
        val r8 = QuarterConsumption(8,2001,"Q4",3f)


        val records = listOf(r1,r2,r3,r4,r5,r6,r7,r8)
        dao.insertAllDataUsageRecords(records)

        viewModel.getUsage(2001)

        val y1GroupedConsumption = GroupedQuarterUsage(2000,"1.0,1.5,2.5,1.0","Q1,Q2,Q3,Q4")
        val y2GroupedConsumption = GroupedQuarterUsage(2001,"2.0,1.5,2.5,3.0","Q1,Q2,Q3,Q4")

        val groupedUsageList = listOf(y1GroupedConsumption,y2GroupedConsumption)

        val expected = QCUIState.OnQuarterUsageAvailable(groupedUsageList,1)

        assertEquals(expected,viewModel.uiState)
    }

    @After
    fun tearDown() {
        database.close()

    }
}