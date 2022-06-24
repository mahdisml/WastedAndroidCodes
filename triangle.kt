Box(modifier = Modifier.rotate(180F)){
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(29.dp)
                                .clip(CustomTriangleShape())
                                .background(Black)
                            ,
                            contentAlignment = Center
                        ) {
                            Column() {
                                Spacer(modifier = Modifier
                                    .width(1.dp)
                                    .height((2.5F).dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .height((16.5F).dp)
                                        .width(20.dp)
                                        .clip(CustomTriangleShape())
                                        .background(Gold)
                                ) {}
                            }
                        }
                    }

class CustomTriangleShape : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}
