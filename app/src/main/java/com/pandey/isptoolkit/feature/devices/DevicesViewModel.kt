package com.pandey.isptoolkit.feature.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.model.DeviceInfo
import com.pandey.isptoolkit.core.network.LanDiscoveryManager
import com.pandey.isptoolkit.core.network.NetworkInfoProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(
    private val lanDiscoveryManager: LanDiscoveryManager,
    private val networkInfoProvider: NetworkInfoProvider
) : ViewModel() {

    private val _devices = MutableStateFlow<List<DeviceInfo>>(emptyList())
    val devices: StateFlow<List<DeviceInfo>> = _devices

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning

    fun startDiscovery() {
        val state = networkInfoProvider.getActiveNetworkState()
        if (state.ipv4Address == "Unavailable" || !state.ipv4Address.contains(".")) return

        val prefix = state.ipv4Address.substringBeforeLast(".")
        _isScanning.value = true

        viewModelScope.launch {
            lanDiscoveryManager.discoverLanDevices(prefix, state.gatewayIp).collect { list ->
                _devices.value = list
            }
            _isScanning.value = false
        }
    }
}