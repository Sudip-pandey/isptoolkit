package com.pandey.isptoolkit.feature.wifi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandey.isptoolkit.ui.components.StatusChip

@Composable
fun WifiScreen(
    navController: NavController,
    viewModel: WifiViewModel = hiltViewModel()
) {
    val scanResults by viewModel.scanResults.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadScanResults()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Discovered Wi-Fi Access Points", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(scanResults) { ap ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = ap.ssid, style = MaterialTheme.typography.titleSmall)
                            StatusChip(statusText = "${ap.rssi} dBm")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "BSSID: ${ap.bssid}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Band: ${ap.band} | Channel: ${ap.channel} (${ap.frequency} MHz)", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}