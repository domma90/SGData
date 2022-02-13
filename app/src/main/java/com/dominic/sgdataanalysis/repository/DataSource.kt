package com.dominic.sgdataanalysis.repository

sealed class DataSource{
    object Api: DataSource()
    object Database: DataSource()
}
