package com.pandey.isptoolkit.feature.subnet

import androidx.lifecycle.ViewModel
import com.pandey.isptoolkit.domain.model.SubnetResult
import com.pandey.isptoolkit.domain.usecase.CalculateSubnetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SubnetCalculatorViewModel @Inject constructor(
    private val calculateSubnetUseCase: CalculateSubnetUseCase
) : ViewModel() {

    private val _result = MutableStateFlow<SubnetResult?>(null)
    val result: StateFlow<SubnetResult?> = _result

    fun calculate(ip: String, cidrStr: String) {
        val cidr = cidrStr.toIntOrNull() ?: 24
        try {
            _result.value = calculateSubnetUseCase(ip, cidr)
        } catch (_: Exception) {}
    }
}