package com.pandey.isptoolkit.feature.ont

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.data.local.dao.OntDao
import com.pandey.isptoolkit.data.local.entity.OntReadingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OntToolViewModel @Inject constructor(
    private val ontDao: OntDao
) : ViewModel() {

    val readings = ontDao.getAllOntReadings()

    fun saveReading(rx: Double, tx: Double, note: String) {
        viewModelScope.launch {
            ontDao.insertOntReading(
                OntReadingEntity(
                    ponStatus = "Up",
                    losStatus = "Normal",
                    rxPowerDbm = rx,
                    txPowerDbm = tx,
                    temperatureC = 42.0,
                    voltageV = 3.3,
                    onuSerial = "HWTC12345678",
                    model = "HG8145V5",
                    oltPortNote = "PON 0/1:4",
                    vlan = 100,
                    technicianNote = note
                )
            )
        }
    }
}