package com.pandey.isptoolkit.app.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Devices : Screen("devices", "Devices")
    object Wifi : Screen("wifi", "Wi-Fi")
    object Tools : Screen("tools", "Tools")
    object History : Screen("history", "History")
    object Settings : Screen("settings", "Settings")
    
    // Sub-feature routes
    object PingTool : Screen("ping_tool", "Ping Monitor")
    object DnsTool : Screen("dns_tool", "DNS Lookup")
    object SubnetTool : Screen("subnet_tool", "Subnet Calculator")
    object OntTool : Screen("ont_tool", "ONT / ONU Tool")
    object PppoeTool : Screen("pppoe_tool", "PPPoE Diagnostic")
    object FullDiagnostic : Screen("full_diagnostic", "Full Diagnostic Engine")
    object CustomerComplaint : Screen("customer_complaint", "Customer Complaint Mode")
    object SpeedTest : Screen("speed_test", "Speed Test")
    object SignalMeter : Screen("signal_meter", "Signal Meter Walk Test")
}