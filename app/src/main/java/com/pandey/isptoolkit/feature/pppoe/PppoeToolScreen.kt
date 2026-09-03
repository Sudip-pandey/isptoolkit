package com.pandey.isptoolkit.feature.pppoe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PppoeToolScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("PPPoE Field Reference", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Standard PPPoE MTU: 1492 Bytes")
                Text("Header Overhead: 8 Bytes (2 Byte PPP + 6 Byte PPPoE)")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Diagnostic Guidance:", style = MaterialTheme.typography.labelLarge)
                Text("If authentication succeeds but websites fail to load, check MTU fragmentation.")
            }
        }
    }
}