package com.example.stepsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.stepsapp.components.AppTopBar
import com.example.stepsapp.components.FloatingBottomBarScreen
import com.example.stepsapp.navigation.AppNavigation
import com.example.stepsapp.ui.theme.StepsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            StepsAppTheme {
                StepsApp()
            }
        }
    }
}

@Composable
fun StepsApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar()
        },
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
        FloatingBottomBarScreen(
            navController = navController
        )
    }
}
