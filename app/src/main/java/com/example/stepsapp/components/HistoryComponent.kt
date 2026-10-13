package com.example.stepsapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepsapp.R
import com.example.stepsapp.models.StepLog
import com.example.stepsapp.components.StepLoggingComponent
import com.example.stepsapp.ui.theme.LightGreen
import java.text.DecimalFormat
import java.time.LocalDate

@Composable
fun HistoryComponent(
    weekStart: LocalDate
) {
    val stepsThisWeek = 20_572
    val averageDailyStep = 8_114
    val records = listOf<StepLog>(
        StepLog(
            steps = 8_432,
            date = LocalDate.now()
        ),
        StepLog(
            steps = 10_180,
            date = LocalDate.of(2026, 10, 11)
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Steps this week",
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = DecimalFormat("#,###").format(stepsThisWeek).replace(",", "."),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val steps = DecimalFormat("#,###").format(averageDailyStep).replace(",", ".")
                Text(
                    text = "A daily average of $steps",
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.check),
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = "Goal achieved"
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    val goalsAchieved = records.count() { it.steps >= 10_000 }
                    Text(
                        text = "$goalsAchieved goals achieved",
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Day by day",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "${records.size} records",
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodySmall
        )
    }

    Spacer(modifier = Modifier.height(14.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        records.forEach { record ->
            StepLoggingComponent(record)
        }

        Text(
            text = "Daily goal: 10.000 steps",
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.labelSmall
        )
    }
}