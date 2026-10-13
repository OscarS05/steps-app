package com.example.stepsapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stepsapp.screens.HistoryScreen
import com.example.stepsapp.screens.HomeScreen
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object History : Screen("history")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.History.route) {
            val today = LocalDate.now()
            var weekStart by remember {
                mutableStateOf(
                    today.with(
                        TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                    )
                )
            }

            HistoryScreen(
                weekStart = weekStart,
                onPreviousWeek = { weekStart = weekStart.minusWeeks(1) },
                onNextWeek = { weekStart = weekStart.plusWeeks(1) },
                canGoNext = weekStart.plusWeeks(1) <= LocalDate.now()
            )
        }
    }
}