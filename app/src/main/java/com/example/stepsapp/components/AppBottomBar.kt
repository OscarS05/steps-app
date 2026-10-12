package com.example.stepsapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stepsapp.navigation.Screen
import com.example.stepsapp.R
import com.example.stepsapp.ui.theme.LightGreen
import com.example.stepsapp.ui.theme.PaleGreen

data class BottomBarNavigation(
    val route: String,
    val icon: Int
)

@Composable
fun FloatingBottomBarScreen(
    navController: NavHostController
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        BottomBarNavigation(
            route = Screen.Home.route,
            icon = R.drawable.home
        ),
        BottomBarNavigation(
            route = Screen.History.route,
            icon = R.drawable.history
        )
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 26.dp, vertical = 30.dp)
                .fillMaxWidth()
                .height(68.dp),
            shape = RoundedCornerShape(36.dp),
            border = BorderStroke(width = 1.dp, color = PaleGreen),
            color = Color.White,
            shadowElevation = 0.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { nav ->
                    val isSelected = currentRoute == nav.route
                    val title = nav.route.replaceFirstChar { it.uppercase() }

                    val selectedColor = if (isSelected) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.onSecondary
                    }

                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .clickable {
                                if (!isSelected) {
                                    navController.navigate(nav.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                        shape = RoundedCornerShape(32.dp),
                        color = if (isSelected) LightGreen else Color.Transparent
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 48.dp,
                                vertical = 14.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(nav.icon),
                                contentDescription = title,
                                tint = selectedColor
                            )

                            Text(
                                text = title,
                                fontWeight = FontWeight.Light,
                                color = selectedColor
                            )
                        }
                    }
                }
            }
        }
    }
}