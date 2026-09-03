package com.pandey.isptoolkit.feature.dns

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DnsToolScreen(viewModel: DnsToolViewModel = hiltViewModel()) {
    var hostInput by remember { mutableStateOf("google.com") }
    val result by viewModel.result.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = hostInput,
            onValueChange = { hostInput = it },
            label = { Text("Domain Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { viewModel.queryDns(hostInput) }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("Resolve DNS A/AAAA")
        }

        Spacer(modifier = Modifier.height(24.dp))
        result?.let { res ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Query Duration: ${res.durationMs} ms", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    res.resolvedAddresses.forEach { ip ->
                        Text(text = "• $ip", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}