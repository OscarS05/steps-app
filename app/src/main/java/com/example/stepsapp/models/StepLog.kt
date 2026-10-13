package com.example.stepsapp.models

import java.time.LocalDate

data class StepLog (
    val date: LocalDate,
    val steps: Int,
)