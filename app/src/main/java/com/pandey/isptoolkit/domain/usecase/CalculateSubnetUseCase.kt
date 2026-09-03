package com.pandey.isptoolkit.domain.usecase

import com.pandey.isptoolkit.core.util.SubnetCalculatorUtil
import com.pandey.isptoolkit.domain.model.SubnetResult
import javax.inject.Inject

class CalculateSubnetUseCase @Inject constructor() {
    operator fun invoke(ip: String, cidr: Int): SubnetResult {
        return SubnetCalculatorUtil.calculateIPv4(ip, cidr)
    }
}