package com.pandey.isptoolkit.feature.ping

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pandey.isptoolkit.ui.components.MetricCard

@Composable
fun PingToolScreen(viewModel: PingToolViewModel = hiltViewModel()) {
    var hostInput by remember { mutableStateOf("8.8.8.8") }
    val summary by viewModel.pingSummary.collectAsState()
    val isTesting by viewModel.isTesting.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = hostInput,
            onValueChange = { hostInput = it },
            label = { Text("Target IP / Hostname") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { viewModel.executePing(hostInput) },
            enabled = !isTesting,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(if (isTesting) "Pinging..." else "Execute Ping Test")
        }

        Spacer(modifier = Modifier.height(24.dp))

        summary?.let { res ->
            Row(modifier = Modifier.fillMaxWidth()) {
                MetricCard("AVG LATENCY", "${res.avgLatencyMs}", "ms", Modifier.weight(1f))
                MetricCard("PACKET LOSS", "${res.packetLossPercent}", "%", Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                MetricCard("MIN LATENCY", "${res.minLatencyMs}", "ms", Modifier.weight(1f))
                MetricCard("MAX LATENCY", "${res.maxLatencyMs}", "ms", Modifier.weight(1f))
            }
        }
    }
}