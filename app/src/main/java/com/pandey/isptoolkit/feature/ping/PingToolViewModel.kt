package com.pandey.isptoolkit.feature.ping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.model.PingSummary
import com.pandey.isptoolkit.core.network.LatencyTester
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PingToolViewModel @Inject constructor(
    private val latencyTester: LatencyTester
) : ViewModel() {

    private val _pingSummary = MutableStateFlow<PingSummary?>(null)
    val pingSummary: StateFlow<PingSummary?> = _pingSummary

    private val _isTesting = MutableStateFlow(false)
    val isTesting: StateFlow<Boolean> = _isTesting

    fun executePing(target: String) {
        if (target.isBlank()) return
        _isTesting.value = true
        viewModelScope.launch {
            val result = latencyTester.executePing(target.trim())
            _pingSummary.value = result
            _isTesting.value = false
        }
    }
}