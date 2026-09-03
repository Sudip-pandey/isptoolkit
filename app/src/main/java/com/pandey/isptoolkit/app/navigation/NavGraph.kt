package com.pandey.isptoolkit.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pandey.isptoolkit.feature.customer.CustomerComplaintScreen
import com.pandey.isptoolkit.feature.devices.DevicesScreen
import com.pandey.isptoolkit.feature.diagnostic.FullDiagnosticScreen
import com.pandey.isptoolkit.feature.dns.DnsToolScreen
import com.pandey.isptoolkit.feature.history.HistoryScreen
import com.pandey.isptoolkit.feature.home.HomeScreen
import com.pandey.isptoolkit.feature.ont.OntToolScreen
import com.pandey.isptoolkit.feature.ping.PingToolScreen
import com.pandey.isptoolkit.feature.pppoe.PppoeToolScreen
import com.pandey.isptoolkit.feature.settings.SettingsScreen
import com.pandey.isptoolkit.feature.signal.SignalMeterScreen
import com.pandey.isptoolkit.feature.speedtest.SpeedTestScreen
import com.pandey.isptoolkit.feature.subnet.SubnetCalculatorScreen
import com.pandey.isptoolkit.feature.tools.ToolsScreen
import com.pandey.isptoolkit.feature.wifi.WifiScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Devices.route) { DevicesScreen(navController) }
        composable(Screen.Wifi.route) { WifiScreen(navController) }
        composable(Screen.Tools.route) { ToolsScreen(navController) }
        composable(Screen.History.route) { HistoryScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
        
        // Detailed Tools Navigation
        composable(Screen.PingTool.route) { PingToolScreen() }
        composable(Screen.DnsTool.route) { DnsToolScreen() }
        composable(Screen.SubnetTool.route) { SubnetCalculatorScreen() }
        composable(Screen.OntTool.route) { OntToolScreen() }
        composable(Screen.PppoeTool.route) { PppoeToolScreen() }
        composable(Screen.FullDiagnostic.route) { FullDiagnosticScreen() }
        composable(Screen.CustomerComplaint.route) { CustomerComplaintScreen() }
        composable(Screen.SpeedTest.route) { SpeedTestScreen() }
        composable(Screen.SignalMeter.route) { SignalMeterScreen() }
    }
}