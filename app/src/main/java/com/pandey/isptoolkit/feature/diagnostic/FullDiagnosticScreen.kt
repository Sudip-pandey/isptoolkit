package com.pandey.isptoolkit.feature.diagnostic

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pandey.isptoolkit.core.model.FullDiagnosticReport
import com.pandey.isptoolkit.ui.components.StatusChip

@Composable
fun FullDiagnosticScreen(viewModel: FullDiagnosticViewModel = hiltViewModel()) {
    val report by viewModel.report.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = { viewModel.runDiagnostics() },
            enabled = !isRunning,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(if (isRunning) "Running System Diagnostics..." else "EXECUTE DIAGNOSTICS")
        }

        Spacer(modifier = Modifier.height(16.dp))

        report?.let { rep ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Health Score: ${rep.healthScore}%", style = MaterialTheme.typography.titleMedium)
                    Text(text = rep.overallSummary, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(rep.results) { res ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(res.testName, style = MaterialTheme.typography.titleSmall)
                                StatusChip(res.status.name)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(res.evidence, style = MaterialTheme.typography.bodySmall)
                            Text(res.explanation, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }
    }
}