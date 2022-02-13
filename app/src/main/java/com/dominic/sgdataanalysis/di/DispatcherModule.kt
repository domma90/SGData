package com.dominic.sgdataanalysis.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/*dispatcher is injected to constructor to test viewmodels easily*/
@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}