package com.pandey.isptoolkit.feature.speedtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.network.SpeedTestManager
import com.pandey.isptoolkit.core.network.SpeedTestProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeedTestViewModel @Inject constructor(
    private val speedTestManager: SpeedTestManager
) : ViewModel() {

    private val _progress = MutableStateFlow<SpeedTestProgress?>(null)
    val progress: StateFlow<SpeedTestProgress?> = _progress

    fun startSpeedTest() {
        viewModelScope.launch {
            speedTestManager.runSpeedTest().collect {
                _progress.value = it
            }
        }
    }
}