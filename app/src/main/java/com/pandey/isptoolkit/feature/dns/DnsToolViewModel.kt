package com.pandey.isptoolkit.feature.dns

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.core.network.DnsRecordResult
import com.pandey.isptoolkit.core.network.DnsResolverManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DnsToolViewModel @Inject constructor(
    private val dnsResolverManager: DnsResolverManager
) : ViewModel() {

    private val _result = MutableStateFlow<DnsRecordResult?>(null)
    val result: StateFlow<DnsRecordResult?> = _result

    fun queryDns(host: String) {
        viewModelScope.launch {
            _result.value = dnsResolverManager.resolveA(host)
        }
    }
}