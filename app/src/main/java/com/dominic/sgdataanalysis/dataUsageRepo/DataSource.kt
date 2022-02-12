package com.dominic.sgdataanalysis.dataUsageRepo

sealed class DataSource{
    object Api: DataSource()
    object Database: DataSource()
}
