package com.pandey.isptoolkit.feature.ont

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OntToolScreen(viewModel: OntToolViewModel = hiltViewModel()) {
    var rxInput by remember { mutableStateOf("-21.5") }
    var txInput by remember { mutableStateOf("2.1") }
    var noteInput by remember { mutableStateOf("Initial fiber power verification") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("ONT Optical Reading Log", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = rxInput,
            onValueChange = { rxInput = it },
            label = { Text("RX Power (dBm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = txInput,
            onValueChange = { txInput = it },
            label = { Text("TX Power (dBm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = noteInput,
            onValueChange = { noteInput = it },
            label = { Text("Technician Note") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val rx = rxInput.toDoubleOrNull() ?: 0.0
                val tx = txInput.toDoubleOrNull() ?: 0.0
                viewModel.saveReading(rx, tx, noteInput)
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Log Optical Reading")
        }
    }
}