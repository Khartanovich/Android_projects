package com.example.jetpackccompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackccompose.R

@Composable
fun MyBottomAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        Modifier.graphicsLayer {
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
            clip = true
        },
        backgroundColor = MaterialTheme.colorScheme.secondary
    ) {
        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == "characters" } == true,
            onClick = {
                navController.navigate("characters") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_people_24),
                    contentDescription = null
                )
            },
            label = {
                Text("Characters")
            },
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.White
        )
        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == "location" } == true,
            onClick = {
                navController.navigate("location") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null
                )
            },
            label = {
                Text("Location")
            },
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.White
        )
    }
}