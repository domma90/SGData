package com.dominic.sgdataanalysis.repository

import com.dominic.sgdataanalysis.domain.repository.DataUsageRepository
import com.dominic.sgdataanalysis.data.repository.DataUsageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataUsageRepoModule {

    @Binds
    abstract fun bindRepo(dataUsageRepoImpl: DataUsageRepositoryImpl): DataUsageRepository
}