package com.pandey.isptoolkit.core.model

enum class TestStatus { PASS, WARNING, FAIL, UNAVAILABLE }

data class DiagnosticTestResult(
    val testName: String,
    val status: TestStatus,
    val measurement: String,
    val evidence: String,
    val explanation: String
)

data class FullDiagnosticReport(
    val timestamp: Long = System.currentTimeMillis(),
    val healthScore: Int,
    val overallSummary: String,
    val results: List<DiagnosticTestResult>
)