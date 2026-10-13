package com.example.stepsapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.sqrt

class AccelerometerTracker(
    context: Context,
    private val onStepDetected: () -> Unit
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accelerometer =
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val gravity = FloatArray(3)

    private val alpha = 0.8f
    private val threshold = 1.2f
    private val minStepInterval = 300_000_000L // 300 ms

    private var previousMagnitude = 0f
    private var lastStepTimestamp = 0L

    fun start(): Boolean {
        val sensor = accelerometer ?: return false

        return sensorManager.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER) return

        Log.d(
            "Accelerometer",
            "X=${event.values[0]}, " +
                    "Y=${event.values[1]}, " +
                    "Z=${event.values[2]}"
        )

        // Estimar la gravedad en los tres ejes
        for (i in 0..2) {
            gravity[i] = alpha * gravity[i] +
                    (1f - alpha) * event.values[i]
        }

        // Restar la gravedad para obtener aceleración lineal aproximada
        val x = event.values[0] - gravity[0]
        val y = event.values[1] - gravity[1]
        val z = event.values[2] - gravity[2]

        // Magnitud del movimiento, independientemente de la orientación
        val magnitude = sqrt(x * x + y * y + z * z)

        val now = event.timestamp

        // Detectar un cruce del umbral y evitar contar
        // varios pasos demasiado seguidos
        if (
            previousMagnitude < threshold &&
            magnitude >= threshold &&
            now - lastStepTimestamp >= minStepInterval
        ) {
            Log.d("StepCounter", "¡Paso detectado!")
            lastStepTimestamp = now
            onStepDetected()
        }

        previousMagnitude = magnitude
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int
    ) = Unit
}
