package com.pandey.isptoolkit.domain.model

enum class ComplaintType {
    INTERNET_SLOW, NO_INTERNET, WEAK_WIFI, HIGH_PING, ONT_LOS
}

data class ComplaintDiagnosis(
    val likelyIssue: String,
    val evidence: String,
    val recommendedAction: String
)