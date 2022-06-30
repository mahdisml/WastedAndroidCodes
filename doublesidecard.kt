DoubleSide(
    rotationX = rotationX,
    rotationY = rotationY,
    rotationZ = rotationZ,
    cameraDistance = 16f,
    flipType = FLIPTYPE.HORIZONTAL,
    front = {
        Image(
            painterResource(R.drawable.card_front),
            contentDescription = "Poker Front",
            modifier = Modifier.border(
                2.dp, Color.Black, shape = roundedDegree)
        )
    }, back = {
        Image(
            painterResource(R.drawable.card_back),
            contentDescription = "Poker Front",
            modifier = Modifier.border(
                2.dp, Color.Black, shape = roundedDegree)
        )
    })

@Composable
fun DoubleSide(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationX: Float = 0f,
    rotationY: Float = 0f,
    rotationZ: Float = 0f,
    cameraDistance: Float = 8f,
    flipType: FLIPTYPE,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    fun isHorizontallyFlip() = (abs(rotationX) % 360 > 90f 
            && abs(rotationX) % 360 < 270f)
    fun isVerticallyFlip() = (abs(rotationY) % 360 > 90f 
            && abs(rotationY) % 360 < 270f)

    fun isFlipped() = isVerticallyFlip() xor isHorizontallyFlip()

    if (isFlipped()) {
        val rotationXBack =
            if (flipType == FLIPTYPE.HORIZONTAL)
                rotationX - 180
            else
                rotationX

        val rotationYBack =
            if (flipType == FLIPTYPE.VERTICAL)
                rotationY - 180
            else
                -rotationY
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationXBack,
                    rotationY = rotationYBack,
                    rotationZ = -rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            back()
        }
    } else {
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            front()
        }
    }
}
