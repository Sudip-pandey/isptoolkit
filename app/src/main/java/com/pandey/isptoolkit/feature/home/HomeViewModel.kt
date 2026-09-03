package com.pandey.isptoolkit.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.model.NetworkState
import com.pandey.isptoolkit.core.network.NetworkInfoProvider
import com.pandey.isptoolkit.core.network.PublicIpProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkInfoProvider: NetworkInfoProvider,
    private val publicIpProvider: PublicIpProvider
) : ViewModel() {

    private val _networkState = MutableStateFlow(NetworkState())
    val networkState: StateFlow<NetworkState> = _networkState

    init {
        refreshNetworkState()
    }

    fun refreshNetworkState() {
        viewModelScope.launch {
            val currentState = networkInfoProvider.getActiveNetworkState()
            _networkState.value = currentState
            
            val publicIp = publicIpProvider.fetchPublicIp()
            _networkState.value = _networkState.value.copy(publicIp = publicIp)
        }
    }
}