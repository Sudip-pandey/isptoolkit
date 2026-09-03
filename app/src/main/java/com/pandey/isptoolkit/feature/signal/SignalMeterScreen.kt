package com.pandey.isptoolkit.feature.signal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pandey.isptoolkit.ui.components.MetricCard

@Composable
fun SignalMeterScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Wi-Fi Signal Walk Test", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            MetricCard(title = "MIN RSSI", value = "-82", unit = "dBm", modifier = Modifier.weight(1f))
            MetricCard(title = "MAX RSSI", value = "-54", unit = "dBm", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            MetricCard(title = "AVG RSSI", value = "-68", unit = "dBm", modifier = Modifier.weight(1f))
            MetricCard(title = "SAMPLES", value = "42", unit = "", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("START MEASUREMENT SESSION")
        }
    }
}