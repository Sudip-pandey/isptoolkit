package com.pandey.isptoolkit.domain.model

data class SubnetResult(
    val ipAddress: String,
    val cidr: Int,
    val networkAddress: String,
    val broadcastAddress: String,
    val subnetMask: String,
    val wildcardMask: String,
    val firstUsableIp: String,
    val lastUsableIp: String,
    val usableHostCount: Long
)