package com.dominic.sgdataanalysis.network
data class DataUsageResponse (
    val help: String,
    val success: Boolean,
    val result: Result
)

data class Result (
    val resourceID: String,
    val fields: List<Field>,
    val records: List<Record>,
    val links: Links,
    val limit: Long,
    val total: Long
)

data class Field (
    val type: String,
    val id: String
)

data class Links (
    val start: String,
    val next: String
)

data class Record (
    val volumeOfMobileData: String,
    val quarter: String,
    val id: Long
)
