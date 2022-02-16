package com.dominic.sgdataanalysis.data.data_source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.dominic.sgdataanalysis.domain.model.GroupedQuarterUsage
import com.dominic.sgdataanalysis.domain.model.QuarterConsumption
import com.dominic.sgdataanalysis.domain.model.YearlyConsumption
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DataUsageDatabaseTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DataUsageDatabase
    private lateinit var dao: DataUsageRecordDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
        DataUsageDatabase::class.java).allowMainThreadQueries().build()
        dao = database.dataUsageRecordDao

    }

    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertUsageRecordListTest() = runBlocking{
        val r1 = QuarterConsumption(1,2000,"Q1",1.5f)
        val r2 = QuarterConsumption(2,2001,"Q1",1.5f)
        val r3 = QuarterConsumption(3,2002,"Q1",1.5f)

        val records = listOf(r1, r2, r3)

        dao.insertAllDataUsageRecords(records)

        val quarterUsageRecord = dao.getAllQuarterUsage().first()

        assertEquals(records,quarterUsageRecord)
    }

    @Test
    fun getYearlyUsage() = runBlocking {
        val r1 = QuarterConsumption(1,2000,"Q1",1f)
        val r2 = QuarterConsumption(2,2000,"Q2",1.5f)
        val r3 = QuarterConsumption(3,2000,"Q3",2.5f)
        val r4 = QuarterConsumption(4,2000,"Q4",1f)

        val r5 = QuarterConsumption(5,2010,"Q1",2f)
        val r6 = QuarterConsumption(6,2010,"Q2",1.5f)
        val r7 = QuarterConsumption(7,2010,"Q3",2.5f)
        val r8 = QuarterConsumption(8,2010,"Q4",3f)


        val records = listOf(r1,r2,r3,r4,r5,r6,r7,r8)
        dao.insertAllDataUsageRecords(records)

        val y1Consumption = YearlyConsumption(2000,6f)
        val y2Consumption = YearlyConsumption(2010,9f)

        val expected = listOf(y1Consumption,y2Consumption)

        val actual = dao.getYearlyUsage().first()

        assertEquals(expected,actual)
    }

    @Test
    fun getGroupedQuarterUsage() = runBlocking{
        val r1 = QuarterConsumption(1,2000,"Q1",1f)
        val r2 = QuarterConsumption(2,2000,"Q2",1.5f)
        val r3 = QuarterConsumption(3,2000,"Q3",2.5f)
        val r4 = QuarterConsumption(4,2000,"Q4",1f)

        val r5 = QuarterConsumption(5,2010,"Q1",2f)
        val r6 = QuarterConsumption(6,2010,"Q2",1.5f)
        val r7 = QuarterConsumption(7,2010,"Q3",2.5f)
        val r8 = QuarterConsumption(8,2010,"Q4",3f)


        val records = listOf(r1,r2,r3,r4,r5,r6,r7,r8)
        dao.insertAllDataUsageRecords(records)

        val y1GroupedConsumption = GroupedQuarterUsage(2000,"1.0,1.5,2.5,1.0","Q1,Q2,Q3,Q4")
        val y2GroupedConsumption = GroupedQuarterUsage(2010,"2.0,1.5,2.5,3.0","Q1,Q2,Q3,Q4")

        val expected = listOf(y1GroupedConsumption,y2GroupedConsumption)

        val actual = dao.getGroupedQuarterUsage()

        assertEquals(expected,actual)

    }
}