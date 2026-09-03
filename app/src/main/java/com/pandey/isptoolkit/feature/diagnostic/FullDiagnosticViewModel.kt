package com.pandey.isptoolkit.feature.diagnostic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.model.FullDiagnosticReport
import com.pandey.isptoolkit.core.network.DiagnosticEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullDiagnosticViewModel @Inject constructor(
    private val diagnosticEngine: DiagnosticEngine
) : ViewModel() {

    private val _report = MutableStateFlow<FullDiagnosticReport?>(null)
    val report: StateFlow<FullDiagnosticReport?> = _report

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    fun runDiagnostics() {
        _isRunning.value = true
        viewModelScope.launch {
            _report.value = diagnosticEngine.runFullDiagnostics()
            _isRunning.value = false
        }
    }
}