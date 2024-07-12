package com.plcoding.navigationdrawercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                                MenuItem(
                                    id = "news",
                                    title = "News",
                                    contentDescription = "Go to news screen",
                                    icon = Icons.Default.Info
                                ),
                                MenuItem(
                                    id = "resources",
                                    title = "Resources",
                                    contentDescription = "Go to resources screen",
                                    icon = Icons.Default.Home
                                )
                            ),
                            onItemClick = { menuItem ->
                                when (menuItem.id) {
                                    "home" -> selectedScreen = Screen.Home
                                    "settings" -> selectedScreen = Screen.Settings
                                    "help" -> selectedScreen = Screen.Help
                                    "news" -> selectedScreen = Screen.News
                                    "resources" -> selectedScreen = Screen.Resources
                                }
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    when (selectedScreen) {
                        Screen.Home -> HomeScreen()
                        Screen.Settings -> SettingsScreen()
                        Screen.Help -> HelpScreen()
                        Screen.News -> NewsScreen()
                        Screen.Resources -> ResourcesScreen()
                    }
                }
            }
        }
    }
}
