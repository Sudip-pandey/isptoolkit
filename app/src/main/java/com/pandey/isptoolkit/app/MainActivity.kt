package com.pandey.isptoolkit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pandey.isptoolkit.app.navigation.NavGraph
import com.pandey.isptoolkit.app.navigation.Screen
import com.pandey.isptoolkit.app.permissions.PermissionHandler
import com.pandey.isptoolkit.ui.theme.ISPToolkitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionHandler.hasPermissions(this)) {
            permissionLauncher.launch(PermissionHandler.getRequiredPermissions().toTypedArray())
        }

        setContent {
            ISPToolkitTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("ISP Toolkit") },
                            actions = {
                                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                            val items = listOf(
                                Screen.Home to Icons.Default.Home,
                                Screen.Devices to Icons.Default.Devices,
                                Screen.Wifi to Icons.Default.Wifi,
                                Screen.Tools to Icons.Default.Build,
                                Screen.History to Icons.Default.History
                            )
                            items.forEach { (screen, icon) ->
                                NavigationBarItem(
                                    icon = { Icon(icon, contentDescription = screen.title) },
                                    label = { Text(screen.title) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}