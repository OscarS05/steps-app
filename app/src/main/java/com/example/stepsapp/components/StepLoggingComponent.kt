package com.example.stepsapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepsapp.models.StepLog
import com.example.stepsapp.ui.theme.LightGreen
import com.example.stepsapp.ui.theme.LightPastelGreen
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun StepLoggingComponent(
    record: StepLog
) {
    val isToday = record.date.dayOfMonth == LocalDate.now().dayOfMonth
    val progressPercentage = (record.steps / 10_000f) * 100f
    val progressBar = (record.steps / 10_000f).coerceIn(0f, 1f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isToday) LightGreen else Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(44.dp)
                    .width(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (isToday) Color.White else LightGreen),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = record.date.dayOfMonth.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    val formatter = DateTimeFormatter.ofPattern("MMM", Locale("es", "CO"))
                    Text(
                        text = record.date.format(formatter),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text =
                            if (isToday) "Today"
                            else record.date.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() },
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = DecimalFormat("#,###").format(record.steps).replace(",", "."),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LinearProgressIndicator(
                    progress = { progressBar },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = LightPastelGreen
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val label =
                        if (progressPercentage >= 100) {
                            "Goal achieved"
                        } else {
                            "In progress · ${progressPercentage.toInt()} % of the goal"
                        }

                    Text(
                        text = label,
                        color =
                            if(progressPercentage >= 100){
                                MaterialTheme.colorScheme.secondary
                            } else {
                                MaterialTheme.colorScheme.onSecondary
                            },
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "steps",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}