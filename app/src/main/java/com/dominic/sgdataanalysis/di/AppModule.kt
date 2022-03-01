package com.dominic.sgdataanalysis.di

import android.app.Application
import androidx.room.Room
import com.dominic.sgdataanalysis.data.data_source.DataUsageDatabase
import com.dominic.sgdataanalysis.data.data_source.DataUsageRecordDao
import com.dominic.sgdataanalysis.data.repository.DataUsageRepositoryImpl
import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesDataUsageDatabase(app: Application): DataUsageDatabase {
        return Room.databaseBuilder(
            app,
            DataUsageDatabase::class.java,
            DataUsageDatabase.DATABASE_NAME
        ).build()
    }

/*
    @Provides
    @Singleton
    fun provideDataUsageRepository(db:DataUsageDatabase):DataUsageRepository{
        return DataUsageRepositoryImpl(db.dataUsageRecordDao)
    }*/


    @Provides
    @Singleton
    fun provideDao(db: DataUsageDatabase):DataUsageRecordDao{
        return db.dataUsageRecordDao
    }

}