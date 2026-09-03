package com.pandey.isptoolkit.feature.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pandey.isptoolkit.domain.model.ComplaintType

@Composable
fun CustomerComplaintScreen(viewModel: CustomerComplaintViewModel = hiltViewModel()) {
    val diagnosis by viewModel.diagnosis.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Guided Complaint Diagnostic", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        ComplaintType.values().forEach { type ->
            Button(
                onClick = { viewModel.diagnose(type) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(type.name.replace("_", " "))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        diagnosis?.let { diag ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Likely Issue: ${diag.likelyIssue}", style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Evidence: ${diag.evidence}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Action: ${diag.recommendedAction}")
                }
            }
        }
    }
}