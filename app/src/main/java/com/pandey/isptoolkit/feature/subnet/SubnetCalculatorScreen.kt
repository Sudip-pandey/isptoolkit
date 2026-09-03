package com.pandey.isptoolkit.feature.subnet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SubnetCalculatorScreen(viewModel: SubnetCalculatorViewModel = hiltViewModel()) {
    var ipInput by remember { mutableStateOf("192.168.1.50") }
    var cidrInput by remember { mutableStateOf("24") }
    val result by viewModel.result.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = ipInput,
                onValueChange = { ipInput = it },
                label = { Text("IP Address") },
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = cidrInput,
                onValueChange = { cidrInput = it },
                label = { Text("CIDR (e.g. 24)") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { viewModel.calculate(ipInput, cidrInput) },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Calculate Subnet")
        }

        Spacer(modifier = Modifier.height(24.dp))
        result?.let { res ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Network Address: ${res.networkAddress}")
                    Text("Broadcast Address: ${res.broadcastAddress}")
                    Text("Subnet Mask: ${res.subnetMask}")
                    Text("Usable Range: ${res.firstUsableIp} - ${res.lastUsableIp}")
                    Text("Total Usable Hosts: ${res.usableHostCount}")
                }
            }
        }
    }
}