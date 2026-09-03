package com.pandey.isptoolkit.feature.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandey.isptoolkit.domain.model.ComplaintDiagnosis
import com.pandey.isptoolkit.domain.model.ComplaintType
import com.pandey.isptoolkit.domain.usecase.RunCustomerWorkflowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerComplaintViewModel @Inject constructor(
    private val runCustomerWorkflowUseCase: RunCustomerWorkflowUseCase
) : ViewModel() {

    private val _diagnosis = MutableStateFlow<ComplaintDiagnosis?>(null)
    val diagnosis: StateFlow<ComplaintDiagnosis?> = _diagnosis

    fun diagnose(type: ComplaintType) {
        viewModelScope.launch {
            _diagnosis.value = runCustomerWorkflowUseCase.diagnose(type)
        }
    }
}