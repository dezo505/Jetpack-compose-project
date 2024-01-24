package com.example.jetpackcomposeproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.jetpackcomposeproject.screen.DetailsScreen
import com.example.jetpackcomposeproject.screen.EditScreen
import com.example.jetpackcomposeproject.screen.ImageScreen
import com.example.jetpackcomposeproject.screen.ListScreen
import com.example.jetpackcomposeproject.screen.PrimaryScreen

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {
    Scaffold(
        topBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState().value
            val currentRoute = navBackStackEntry?.destination?.route

            TopAppBar(
                title = {
                    Text(
                        text = when (currentRoute) {
                            Screen.ImageScreen.route -> "Image Selection"
                            Screen.PrimaryScreen.route -> "Home"
                            Screen.ListScreen.route -> "List"
                            else -> "Unknown Screen"
                        },
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    BottomNavigationItem(
                        title = "Image Selection",
                        route = Screen.ImageScreen.route,
                        selectedIcon = Icons.Filled.Edit,
                        unselectedIcon = Icons.Outlined.Edit,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Home",
                        route = Screen.PrimaryScreen.route,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "List",
                        route = Screen.ListScreen.route,
                        selectedIcon = Icons.Filled.List,
                        unselectedIcon = Icons.Outlined.List,
                        hasNews = false
                    )
                )

                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                                    if(currentRoute == item.route)
                                        Icon(imageVector = item.selectedIcon, contentDescription = null)
                                    else
                                        Icon(imageVector = item.unselectedIcon, contentDescription = null)
                               },
                        label = { item.title },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = Screen.PrimaryScreen.route) {
                composable(route = Screen.ImageScreen.route) {
                    ImageScreen()
                }
                composable(route = Screen.PrimaryScreen.route) {
                    PrimaryScreen()
                }
                composable(Screen.ListScreen.route) {
                    ListScreen(navController = navController)
                }
                composable(
                    route = Screen.DetailsScreen.route,
                    arguments = listOf(
                        navArgument("person_id") {
                            type = NavType.LongType
                            defaultValue = -1
                            nullable = false
                        }
                    )
                ) { entry ->
                    DetailsScreen(
                        navController = navController,
                        id = entry.arguments?.getLong("person_id")!!
                    )
                }
                composable(
                    route = Screen.EditScreen.route,
                    arguments = listOf(
                        navArgument("person_id") {
                            type = NavType.LongType
                            defaultValue = -1
                            nullable = false
                        }
                    )
                ) { entry ->
                    EditScreen(
                        navController = navController,
                        id = entry.arguments?.getLong("person_id")!!
                    )
                }
            }
        }
    }
}