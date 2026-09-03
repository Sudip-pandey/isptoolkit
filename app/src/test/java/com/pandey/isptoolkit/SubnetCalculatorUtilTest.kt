package com.pandey.isptoolkit

import com.pandey.isptoolkit.core.util.SubnetCalculatorUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class SubnetCalculatorUtilTest {

    @Test
    fun testStandardClassCSubnetCalculation() {
        val result = SubnetCalculatorUtil.calculateIPv4("192.168.1.50", 24)
        
        assertEquals("192.168.1.0", result.networkAddress)
        assertEquals("192.168.1.255", result.broadcastAddress)
        assertEquals("255.255.255.0", result.subnetMask)
        assertEquals("192.168.1.1", result.firstUsableIp)
        assertEquals("192.168.1.254", result.lastUsableIp)
        assertEquals(254L, result.usableHostCount)
    }
}