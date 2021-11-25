package com.strukovsky.financiel.analyzers

data class Analysis(
    val recommendation: Recommendations,
    val message: String,
    val value: String,
    val field: String
)