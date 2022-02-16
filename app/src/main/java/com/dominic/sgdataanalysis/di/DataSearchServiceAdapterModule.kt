package com.dominic.sgdataanalysis.di

import com.dominic.sgdataanalysis.network.adapter.DataSearchServiceAdapterImpl
import com.dominic.sgdataanalysis.network.adapter.DataStoreSearchServiceAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSearchServiceAdapterModule {

    @Singleton
    @Binds
    abstract fun bindAdapter(dataSearchServiceAdapterImpl: DataSearchServiceAdapterImpl):DataStoreSearchServiceAdapter
}