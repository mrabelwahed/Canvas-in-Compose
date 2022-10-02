package com.ramadan.canvas

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ramadan.canvas.ui.theme.CanvasTheme
import com.ramadan.canvas.ui.theme.Purple200
import com.ramadan.canvas.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    SquareComponent(sizeInDp = 100.dp  , Color.Blue)
                }
            }
        }
    }
}

@Composable
fun SquareComponent(sizeInDp : Dp =  300.dp , color: Color){
    val canvasSizeInPx = with(LocalDensity.current){
       sizeInDp.toPx()
    }

    val infiniteScale = rememberInfiniteTransition()
    val animatedDotScale by infiniteScale.animateFloat(
        initialValue = 20f,
        targetValue = canvasSizeInPx/2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.size(sizeInDp)){
           rotate(20f){
               drawRect(
                   brush = Brush.linearGradient(listOf(Purple200 , Teal200)),
                   size = size
               )
           }

            println("size is ${size.toDpSize()}")
            drawCircle(
                color = Color.White,
                center = Offset(x = size.width/2f , size.height/2f),
                radius = animatedDotScale
            )
        }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasTheme {
        Greeting("Android")
    }
}