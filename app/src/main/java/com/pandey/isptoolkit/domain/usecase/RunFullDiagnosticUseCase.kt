package com.pandey.isptoolkit.domain.usecase

import com.pandey.isptoolkit.core.model.FullDiagnosticReport
import com.pandey.isptoolkit.core.network.DiagnosticEngine
import javax.inject.Inject

class RunFullDiagnosticUseCase @Inject constructor(
    private val diagnosticEngine: DiagnosticEngine
) {
    suspend operator fun invoke(): FullDiagnosticReport = diagnosticEngine.runFullDiagnostics()
}
