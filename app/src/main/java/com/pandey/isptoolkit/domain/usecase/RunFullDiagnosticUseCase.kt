package com.pandey.isptoolkit.domain.usecase

import com.pandey.isptoolkit.core.model.DiagnosticResult
import com.pandey.isptoolkit.core.network.DiagnosticEngine

class RunFullDiagnosticUseCase(
    private val diagnosticEngine: DiagnosticEngine = DiagnosticEngine()
) {
    operator fun invoke(): DiagnosticResult = diagnosticEngine.runDiagnostics()
}
