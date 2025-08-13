package com.example.littlelemon.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.database.AppDatabase
import com.example.littlelemon.screens.Home
import com.example.littlelemon.screens.Onboarding
import com.example.littlelemon.screens.Profile
import com.example.littlelemon.sharedpreference.PreferencesManager
import com.example.littlelemon.sharedpreference.USER_FIRST_KEY

@Composable
fun NavigationComposable(
    modifier: Modifier,
    database: AppDatabase,
    navController: NavHostController
) {
    val context = LocalContext.current
    val preferences = PreferencesManager(context)
    val startDestination = if (!preferences.getString(USER_FIRST_KEY).isNullOrEmpty()) {
        Home.route
    } else {
        Onboarding.route
    }

    NavHost(
        modifier = Modifier.background(Color.White),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Home.route) {
            Home(modifier = modifier, database = database) {
                navController.navigate(route = Profile.route)
            }
        }
        composable(Profile.route) {
            Profile(modifier) {
                navController.navigate(route = Onboarding.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Onboarding.route) {
            Onboarding(modifier) {
                navController.navigate(route = Home.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        }
    }
}