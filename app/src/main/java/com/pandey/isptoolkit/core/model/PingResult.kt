package com.pandey.isptoolkit.core.model

data class PingSample(
    val sequence: Int,
    val latencyMs: Long,
    val isSuccess: Boolean,
    val errorMessage: String? = null
)

data class PingSummary(
    val targetHost: String,
    val totalSent: Int,
    val totalReceived: Int,
    val packetLossPercent: Float,
    val minLatencyMs: Long,
    val maxLatencyMs: Long,
    val avgLatencyMs: Long,
    val samples: List<PingSample>
)