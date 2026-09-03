package com.pandey.isptoolkit.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandey.isptoolkit.app.navigation.Screen
import com.pandey.isptoolkit.ui.components.MetricCard
import com.pandey.isptoolkit.ui.components.StatusChip

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.networkState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = state.ssid, style = MaterialTheme.typography.titleLarge)
                    StatusChip(statusText = if (state.isConnected) "CONNECTED" else "DISCONNECTED")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type: ${state.connectionType} | Band: ${state.wifiBand}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "IPv4: ${state.ipv4Address}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Gateway: ${state.gatewayIp}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Public IP: ${state.publicIp}", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Quick Diagnostics Metrics", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            MetricCard(title = "RSSI", value = "${state.rssi}", unit = "dBm", modifier = Modifier.weight(1f))
            MetricCard(title = "LINK SPEED", value = "${state.linkSpeedMbps}", unit = "Mbps", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Field Actions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate(Screen.FullDiagnostic.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("RUN FULL DIAGNOSTIC")
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { navController.navigate(Screen.CustomerComplaint.route) },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("CUSTOMER COMPLAINT MODE")
        }
    }
}