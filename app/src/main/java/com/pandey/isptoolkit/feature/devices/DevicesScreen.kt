package com.pandey.isptoolkit.feature.devices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandey.isptoolkit.ui.components.StatusChip

@Composable
fun DevicesScreen(
    navController: NavController,
    viewModel: DevicesViewModel = hiltViewModel()
) {
    val devices by viewModel.devices.collectAsState()
    val isScanning by viewModel.isScanning.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Authorized LAN Discovery", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { viewModel.startDiscovery() }, enabled = !isScanning) {
                Text(if (isScanning) "Scanning..." else "Scan LAN")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (devices.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No devices discovered yet. Tap 'Scan LAN' to discover authorized active subnet hosts.")
            }
        } else {
            LazyColumn {
                items(devices) { device ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = device.ipAddress, style = MaterialTheme.typography.bodyLarge)
                                Text(text = device.hostname ?: "Unknown Hostname", style = MaterialTheme.typography.bodySmall)
                                Text(text = "Type: ${device.deviceType} (${device.confidence})", style = MaterialTheme.typography.labelSmall)
                            }
                            StatusChip(statusText = "${device.latencyMs} ms")
                        }
                    }
                }
            }
        }
    }
}