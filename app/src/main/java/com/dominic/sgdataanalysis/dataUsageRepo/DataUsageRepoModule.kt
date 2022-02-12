package com.dominic.sgdataanalysis.dataUsageRepo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataUsageRepoModule {

    @Binds
    abstract fun bindRepo(dataUsageRepoImpl: DataUsageRepoImpl):DataUsageRepo
}