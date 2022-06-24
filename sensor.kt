    var gyro by remember { mutableStateOf("sss") }
    var axisX by remember { mutableStateOf(0f) }
    var axisY by remember { mutableStateOf(0f) }
    var axisZ by remember { mutableStateOf(0f) }


    var xxx = remember { Animatable(0.0f)}
    var yyy = remember { Animatable(0.0f)}


    LaunchedEffect(true) {
        launch {
            xxx.animateTo(
                targetValue = axisX,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            ) {}
        }
        launch {
            yyy.animateTo(
                targetValue = axisY,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            ) {}
        }
    }

    //sensor

    val sensorManager = LocalContext.current.getSystemService(SENSOR_SERVICE) as SensorManager
    val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    // Create a constant to convert nanoseconds to seconds.
    val NS2S = 1.0f / 1000000000.0f
    val deltaRotationVector = FloatArray(4) { 0f }
    var timestamp: Float = 0f

    class SensorEventListener ():android.hardware.SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            try {
                event?.let {
                    axisX = (event.values[0]*100).toInt().toFloat()
                    axisY = (event.values[1]*100).toInt().toFloat()
                    axisZ = (event.values[2]*100).toInt().toFloat()
                    gyro = "$axisX,$axisY,$axisZ"
                }
            } catch (e: Exception) { }

        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }


    }
    sensorManager.registerListener(SensorEventListener(),sensor,SensorManager.SENSOR_DELAY_NORMAL)

    //sensor
