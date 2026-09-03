package com.pandey.isptoolkit.feature.speedtest

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pandey.isptoolkit.ui.components.MetricCard

@Composable
fun SpeedTestScreen(viewModel: SpeedTestViewModel = hiltViewModel()) {
    val progress by viewModel.progress.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ISP Throughput Test", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))

        MetricCard("DOWNLOAD SPEED", "%.2f".format(progress?.currentMbps ?: 0.0), "Mbps", Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = (progress?.progressPercent ?: 0) / 100f,
            modifier = Modifier.fillMaxWidth().height(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.startSpeedTest() },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("START SPEED TEST")
        }
    }
}