package com.example.stepsapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepsapp.R
import com.example.stepsapp.ui.theme.LightGreen
import com.example.stepsapp.ui.theme.LightPastelGreen
import java.text.DecimalFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun HomeScreen(){
    val currentDate = LocalDateTime.now()
    val formatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.FULL)
        .withLocale(Locale("es", "ES"))
    val naturalDate = currentDate.format(formatter).replaceFirstChar { it.uppercase() }

    val progress = 0.5.toFloat()
    val percentageProgress = 80
    val totalSteps = 4560
    val accumulatedSteps = 20_572

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = naturalDate,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = "Every step counts.",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(284.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGreen
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(18.dp),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(
                    progress = { progress },
                    color = LightPastelGreen,
                    trackColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(220.dp)
                        .graphicsLayer {
                            scaleX = -1f
                        },
                    strokeWidth = 10.dp,
                    strokeCap = StrokeCap.Round
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.steps),
                        contentDescription = "Steps Icon",
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = totalSteps.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.displayLarge
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "today's steps",
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        text = "$percentageProgress% of 10.000",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(252.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your week",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleMedium
                    )

                    val today = LocalDate.now()
                    val monday = today.with(
                        TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                    )
                    val sunday = monday.plusDays(6)

                    val formatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)
                    val dateRange = "${monday.format(formatter)} - ${sunday.format(formatter)}"


                    Text(
                        text = dateRange,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                BarChart()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = DecimalFormat("#,###").format(accumulatedSteps).replace(",", "."),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "Hoy",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.plant),
                contentDescription = "Health icon",
                tint = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "At your own pace, you're doing great.",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium
                )

                val remainingSteps = (10_000 - totalSteps).coerceAtLeast(0)

                Text(
                    text = "You have $remainingSteps steps left to reach your goal.",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

fun BarChart(){
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val steps = listOf(3200f, 4500f, 2800f, 6100f, 0f, 0f, 0f)
}