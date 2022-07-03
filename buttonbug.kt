@Composable
fun Button(){
    var clickable by remember { mutableStateOf(true)}
    val job = rememberCoroutineScope()
    val cardY = remember { Animatable(360.0f) }

    Box (modifier = Modifier
        .width(200.dp)
        .height(100.dp)
        .graphicsLayer {
            cameraDistance = 30.0f
            rotationY = cardY.value
        }
        .background(Color.Black)
    ) {
        Box (
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.White)
                .clickable (clickable) {
                    job.launch {
                        clickable = false
                        cardY.animateTo(
                            targetValue = 180f,
                            tween(
                                300,
                                easing = LinearEasing
                            )
                        ) {}
                        cardY.animateTo(
                            targetValue = 360f,
                            tween(
                                300,
                                easing = LinearEasing
                            )
                        ) {}
                        clickable = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Rotate")
        }
    }
}
