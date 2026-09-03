package com.pandey.isptoolkit.feature.wifi

import androidx.lifecycle.ViewModel
import com.pandey.isptoolkit.core.model.WifiScanResultInfo
import com.pandey.isptoolkit.core.network.WifiInfoProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WifiViewModel @Inject constructor(
    private val wifiInfoProvider: WifiInfoProvider
) : ViewModel() {

    private val _scanResults = MutableStateFlow<List<WifiScanResultInfo>>(emptyList())
    val scanResults: StateFlow<List<WifiScanResultInfo>> = _scanResults

    fun loadScanResults() {
        _scanResults.value = wifiInfoProvider.getScanResults()
    }
}