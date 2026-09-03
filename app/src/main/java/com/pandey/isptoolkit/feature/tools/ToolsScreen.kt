package com.pandey.isptoolkit.feature.tools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pandey.isptoolkit.app.navigation.Screen

data class ToolItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun ToolsScreen(navController: NavController) {
    val toolItems = listOf(
        ToolItem("Ping Monitor", Icons.Default.Speed, Screen.PingTool.route),
        ToolItem("DNS Lookup", Icons.Default.Dns, Screen.DnsTool.route),
        ToolItem("Subnet Calculator", Icons.Default.Calculate, Screen.SubnetTool.route),
        ToolItem("ONT / ONU Check", Icons.Default.Router, Screen.OntTool.route),
        ToolItem("PPPoE Diagnostic", Icons.Default.VpnKey, Screen.PppoeTool.route),
        ToolItem("Speed Test", Icons.Default.NetworkCheck, Screen.SpeedTest.route),
        ToolItem("Signal Meter", Icons.Default.WifiTethering, Screen.SignalMeter.route),
        ToolItem("Full Diagnostics", Icons.Default.CheckCircle, Screen.FullDiagnostic.route)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "ISP Technician Toolset", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(toolItems) { tool ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable { navController.navigate(tool.route) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(tool.icon, contentDescription = tool.title, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(tool.title, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}