package com.example.stepsapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepsapp.R
import com.example.stepsapp.ui.theme.LightGreen
import com.example.stepsapp.ui.theme.LightPastelGreen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = naturalDate,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Every step counts.",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
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
                    strokeWidth = 12.dp,
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
    }
}